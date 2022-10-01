package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin/cashierorder")
@ResponseBody
public class CashierorderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrdertypeService ordertypeService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private VenuesService venuesService;
    @Autowired
    private UserService userService;
    @Autowired
    private  InstructororderService instructororderService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv,
                             HttpServletRequest request
                             ){
        Map<String,Object> queryMap = new HashMap<>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        queryMap.put("cashierid",userid);
        queryMap.put("roleId",2);
        mv.addObject("ordertypelist",ordertypeService.findList());
        mv.addObject("timelist",timeService.findList(queryMap));
        mv.addObject("memberlist",memberService.findList(queryMap));
        mv.addObject("venuesList", venuesService.findListbycashierid(queryMap));
        mv.setViewName("cashierorder/list");
        return mv;
    }
    //获取订单
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page, HttpServletRequest request,
                                   @RequestParam(value = "datestr",required = false) String date,
                                   @RequestParam(value = "ordertypeid",required = false) Integer ordertypeid,
                                   @RequestParam(value = "venuesid",required = false) Integer venuesid,
                                   @RequestParam(value = "timeid",required = false) Integer timeid,
                                   @RequestParam(value = "username",required = false) String username){
        Map<String,Object> ret = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        if (Objects.equals(date,"")){
            date=null;
        }
        Long memberid = null;
        if (username!=null&&username!="") {
            User user = userService.findByUsername(username);
            Member member = memberService.findbyuserid(user.getId());
            memberid = member.getMemberid();
        }
        queryMap.put("date",date);
        queryMap.put("ordertypeid",ordertypeid);
        queryMap.put("venuesid",venuesid);
        queryMap.put("memberid",memberid);
        queryMap.put("timeid",timeid);
        queryMap.put("cashierid",userid);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows",orderService.findListbycashierid(queryMap));
        ret.put("total",orderService.getTotalbycashierid(queryMap));
        return ret;
    }
    //取消订单
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Map<String, String> delete(HttpServletRequest request,
                                      @RequestParam(value = "orderid",required = false) int orderid,
                                      @RequestParam(value = "orderdate",required = false) String orderdate,
                                      @RequestParam(value = "price",required = false) Double price,
                                      @RequestParam(value = "memberid",required = false) long memberid
    ) throws Exception{
        Member member = memberService.findbymemberid(memberid);
        double balance;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(orderdate, fmt);LocalDate date1 = LocalDate.now();
        Map<String, String> ret = new HashMap<String, String>();
        //校验数据，具体步骤进行折叠
        if(StringUtils.isEmpty(orderdate)&&StringUtils.isEmpty(orderid)){
            ret.put("type", "error");
            ret.put("msg", "选择要取消的订单！");
            return ret;
        }//选择取消订单
        if(date1.compareTo(date) < 0){
            //同时取消订单预约的教练
            instructororderService.deletebyorderid(orderid);
            //取消订单将金额退回会员账户
            if(orderService.delete(String.valueOf(orderid))>0){
                balance = member.getBalance()+price;
                memberService.editmember(balance, member.getId());
                ret.put("type", "success");ret.put("msg", "订单取消成功成功！");return ret;
            }
        }
        ret.put("type", "error");ret.put("msg", "订单已经过期不可操作");return ret;
    }
}
