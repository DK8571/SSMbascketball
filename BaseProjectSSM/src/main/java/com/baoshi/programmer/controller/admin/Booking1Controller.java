package com.baoshi.programmer.controller.admin;


import com.baoshi.programmer.entity.admin.Booking;
import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.Venues;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/admin/booking1")
public class Booking1Controller {
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
    private BookingService bookingService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv,
                             HttpServletRequest request,
                             @RequestParam(value = "id",required = true) Long id){
        Map<String,Object> queryMap = new HashMap<>();
        Map<String,Object> venues = new HashMap<>();
        request.getSession().setAttribute("stadiumid",id);
        venues.put("stadiumId",id);
        Long userid = (Long) request.getSession().getAttribute("adminid");
        mv.addObject("ordertypelist",ordertypeService.findList());
        mv.addObject("timelist",timeService.findList(queryMap));
        mv.addObject("memberlist",memberService.findbyuserid(userid));
        mv.addObject("venuesList", venuesService.findList(venues));
        mv.addObject("stadiumId", id);
        mv.setViewName("booking/list1");
        return mv;
    }
    //获取预约球场不同时间的状态和预约人数
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page, HttpServletRequest request,
                                   @RequestParam(value = "datestr",required = false) String date,
                                   @RequestParam(value = "venuesid",required = false) Integer venuesid
    ){
        Map<String,Object> ret = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        if (date != null&&date!="") {
            queryMap.put("date",date);
            if (venuesid != -1){
                queryMap.put("venuesid",venuesid);
                ret.put("rows",bookingService.findList(queryMap));
                ret.put("total",bookingService.getTotal(queryMap));
                HttpSession session = request.getSession();
                //将日期球场id放入session域
                session.setAttribute("date", date);
                session.setAttribute("venuesid", venuesid);
            }
        }
        return ret;
    }
    //预约
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Map<String,String> edit(HttpServletRequest request,Booking booking){
        Map<String,String> ret = new HashMap<>();
        //获取所选场地，会员
        Long userid = (Long) request.getSession().getAttribute("adminid");
        Venues venues = venuesService.findbyVenusId(Long.valueOf(String.valueOf(request.getSession().getAttribute("venuesid"))));
        Member member = memberService.findbyuserid(userid);
        //将下单会员id，预约日期，预约球场id存入预约类
        booking.setMemberid(member.getMemberid());
        booking.setDatestr((String) request.getSession().getAttribute("date"));
        booking.setVenuesid(Long.valueOf(String.valueOf(request.getSession().getAttribute("venuesid"))));
        //计算散客预约价格，余额，返回金额
        double price = venues.getPrice()*booking.getNumber();
        double balance = 0;double out = 0;
        //场地时间状态为“无人预约”
        if (booking.getQuote()==0) {
            //预约类型为比赛
            if (booking.getOrdertypeid()==1) {
                if (member.getBalance()<(venues.getAllprice())){
                    ret.put("type","error");ret.put("msg","余额不足请充值");return ret;
                }else {//完成消费生成订单
                    double priceall = venues.getAllprice();
                    booking.setPrice(priceall);out = priceall;
                    balance = member.getBalance() - priceall;
                    memberService.editmember(balance, userid);
                    //更改状态
                    booking.setQuote(1);
                    bookingService.add(booking);
                }//预约类型为娱乐/训练
            } else{
                //判断预约人数是否超出上限
                if (booking.getSum()+booking.getNumber()>venues.getMax()) {
                    ret.put("type","error");ret.put("msg","超出球场人数上限");return ret;
                } else {
                    if (member.getBalance()<price){
                        ret.put("type","error");ret.put("msg","余额不足请充值");return ret;
                    }else {//完成消费生成订单
                        booking.setPrice(price);out = price;
                        balance = member.getBalance()-price;
                        memberService.editmember(balance, userid);
                        //更改状态
                        booking.setQuote(2);
                        bookingService.add(booking);
                    }
                }
            }
        }
        //场地时间状态为“不可预约”
        else if(booking.getQuote()==1){
            ret.put("type","error");ret.put("msg","不可预约");return ret;
        }
        //场地时间状态为“可预约娱乐/训练”
        else if(booking.getQuote()==2){
            //预约类型为比赛
            if (booking.getOrdertypeid()==1) {
                ret.put("type","error");ret.put("msg","场地不空不可预约比赛");return ret;
            //预约类型为娱乐/训练
            }else{//判断预约人数是否超出上限
                if (booking.getSum()+booking.getNumber()>venues.getMax()) {
                    ret.put("type","error");ret.put("msg","超出球场人数上限");return ret;
                } else {
                    if (member.getBalance()<price){
                        ret.put("type","error");ret.put("msg","余额不足请充值");return ret;
                    }else {//完成消费生成订单
                        booking.setPrice(price);
                        out = price;
                        balance = member.getBalance()-price;
                        memberService.editmember(balance, userid);
                        //更改状态
                        booking.setQuote(2);
                        bookingService.add(booking);
                    }
                }
            }
        }
        ret.put("type", "success");
        ret.put("msg", "预约成功！消费："+out);
        ret.put("balance", String.valueOf(balance));
        out = 0;
        return ret;
    }
}
