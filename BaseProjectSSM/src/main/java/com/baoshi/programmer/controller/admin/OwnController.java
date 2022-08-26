package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.Order;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin/own")
@ResponseBody
public class OwnController {
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


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){
        Map<String,Object> queryMap = new HashMap<>();
        mv.addObject("ordertypelist",ordertypeService.findList());
        mv.addObject("timelist",timeService.findList(queryMap));
        mv.addObject("venuesList", venuesService.findList(queryMap));
        mv.setViewName("own/list");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page,
                                   HttpServletRequest request,
                                   @RequestParam(value = "datestr",required = false) String date,
                                   @RequestParam(value = "ordertypeid",required = false) Integer ordertypeid,
                                   @RequestParam(value = "venuesid",required = false) Integer venuesid,
                                   @RequestParam(value = "timeid",required = false) Integer timeid

    ){
        Map<String,Object> ret = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        Member member = memberService.findbyuserid(userid);
        if (Objects.equals(date,"")){
            date=null;
        }
        queryMap.put("date",date);
        queryMap.put("memberid",member.getMemberid());
        queryMap.put("ordertypeid",ordertypeid);
        queryMap.put("venuesid",venuesid);
        queryMap.put("timeid",timeid);
        ret.put("rows",orderService.findListbyuserid(queryMap));
        ret.put("total",orderService.getTotalbyuserid(queryMap));
        return ret;
    }

    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(HttpServletRequest request,
                                      @RequestParam(value = "orderid",required = false) int orderid,
                                      @RequestParam(value = "orderdate",required = false) String orderdate,
                                      @RequestParam(value = "price",required = false) Double price
    ) throws Exception{
        Long userid = (Long) request.getSession().getAttribute("adminid");
        Member member = memberService.findbyuserid(userid);
        double balance;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(orderdate, fmt);
        LocalDate date1 = LocalDate.now();
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(orderdate)&&StringUtils.isEmpty(orderid)){
            ret.put("type", "error");
            ret.put("msg", "选择要删除的数据！");
            return ret;
        }
        if(date1.compareTo(date) < 0){
            if(orderService.delete(String.valueOf(orderid))>0){
                balance = member.getBalance()+price;
                memberService.editmember(balance, userid);
                ret.put("type", "success");
                ret.put("msg", "用户删除成功！");
                return ret;
            }
        }
        ret.put("type", "error");
        ret.put("msg", "订单已经过期不可操作");
        return ret;
    }
}
