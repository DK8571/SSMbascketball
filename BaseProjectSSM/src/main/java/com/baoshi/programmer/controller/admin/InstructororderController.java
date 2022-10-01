package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.Order;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.entity.admin.Venues;
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

@Controller
@RequestMapping("admin/instructororder")
@ResponseBody
public class InstructororderController {
    @Autowired
    private InstuctorService instuctorService;
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private VenuesService venuesService;
    @Autowired
    private  InstructororderService instructororderService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request,ModelAndView mv,
                             @RequestParam(name = "id",required = true) Long orderid,
                             @RequestParam(name = "datestr",required = true) String datestr
                             ){
        request.getSession().setAttribute("datestr",datestr);
        request.getSession().setAttribute("orderid",orderid);
        mv.setViewName("instructororder/list");
        return mv;
    }
    //获取可预约和已预约的教练
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String, Object> getList(Page page, HttpServletRequest request,
                                       @RequestParam(name = "name",required = false,defaultValue = "") String instuctorname,
                                       @RequestParam(name = "sex",required = false) Integer sex
    ) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //获取登录用户信息
        Long userid = (Long) request.getSession().getAttribute("adminid");
        User user = userService.findbyuserid(userid);
        Long orderid = (Long) request.getSession().getAttribute("orderid");
        //获取订单信息
        Order order = orderService.findbyorderid(queryMap);
        //获取场地信息
        Venues venues = venuesService.findbyVenusId(order.getVenuesid());
        queryMap.put("roleId" , user.getRoleId());queryMap.put("instuctorname", instuctorname);
        queryMap.put("orderid",orderid);
        queryMap.put("sex",sex);
        queryMap.put("stadiumId",venues.getStadiumid());queryMap.put("timeid",order.getTimeid());
        queryMap.put("datestr",order.getDatestr());queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", instructororderService.findList(queryMap));
        resultMap.put("total", instructororderService.getTotal(queryMap));
        return resultMap;
    }
    //预约教练
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, String> add(HttpServletRequest request,
                                      @RequestParam(value = "instructorid",required = false) Long instructorid
    ){
        Map<String, String> ret = new HashMap<String, String>();
        String datestr = (String) request.getSession().getAttribute("datestr");
        Long orderid = (Long) request.getSession().getAttribute("orderid");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestr, fmt);
        LocalDate date1 = LocalDate.now();
        //校验数据，具体步骤进行折叠
        if(date1.compareTo(date) >= 0){
            ret.put("type", "error");
            ret.put("msg", "订单已经过期不可操作");
            return ret;
        }
        //判断用户是否已经预约一个教练
        if(instructororderService.find(instructorid,orderid)>0){
            ret.put("type", "error");ret.put("msg", "您已预约一位教练");return ret;
        }
        if(instructororderService.add(instructorid,orderid)<=0){
            ret.put("type", "error");ret.put("msg", "预约失败");return ret;
        }
        ret.put("type", "success");ret.put("msg", "预约成功");return ret;
    }
    //取消预约教练
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Map<String, String> delete(HttpServletRequest request,
                                      @RequestParam(value = "instructorid",required = false) Long instructorid
    ){
        Map<String, String> ret = new HashMap<String, String>();
        Long orderid = (Long) request.getSession().getAttribute("orderid");
        String datestr = (String) request.getSession().getAttribute("datestr");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestr, fmt);
        LocalDate date1 = LocalDate.now();
        if(date1.compareTo(date) >= 0){
            ret.put("type", "error");
            ret.put("msg", "订单已经过期不可操作");
            return ret;
        }
        if(instructororderService.delete(instructorid,orderid)<=0){
            ret.put("type", "error");
            ret.put("msg", "取消失败");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "取消成功");
        return ret;
    }
}
