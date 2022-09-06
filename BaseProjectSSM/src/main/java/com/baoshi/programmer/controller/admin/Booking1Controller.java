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
        mv.addObject("ordertypelist",ordertypeService.findList());
        mv.addObject("timelist",timeService.findList(queryMap));
        mv.addObject("memberlist",memberService.findList(queryMap));
        mv.addObject("venuesList", venuesService.findList(venues));
        mv.addObject("stadiumId", id);
        mv.setViewName("booking/list1");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page,
                                   HttpServletRequest request,
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
                session.setAttribute("date", date);
                session.setAttribute("venuesid", venuesid);
            }
        }
        return ret;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Map<String,String> edit(HttpServletRequest request,Booking booking){
        Map<String,String> ret = new HashMap<>();
        booking.setDatestr((String) request.getSession().getAttribute("date"));
        Long userid = (Long) request.getSession().getAttribute("adminid");
        booking.setVenuesid(Long.valueOf(String.valueOf(request.getSession().getAttribute("venuesid"))));
        Venues venues = venuesService.findbyVenusId(Long.valueOf(String.valueOf(request.getSession().getAttribute("venuesid"))));
        Member member = memberService.findbyuserid(userid);
        booking.setMemberid(member.getMemberid());
        double price = venues.getPrice()*booking.getNumber();
        double balance;
        if (booking.getQuote()==0) {
            if (booking.getOrdertypeid()==1) {
                    ///
                if (member.getBalance()<(venues.getAllprice())){
                    ret.put("type","error");
                    ret.put("msg","余额不足请充值");
                    return ret;
                }else {
                    double priceall = venues.getAllprice();
                    booking.setPrice(priceall);
                    balance = member.getBalance() - priceall;
                    memberService.editmember(balance, userid);
                    booking.setQuote(1);
                    bookingService.add(booking);
                }
            }else{
                if (booking.getSum()+booking.getNumber()>venues.getMax()) {
                    ret.put("type","error");
                    ret.put("msg","超出球场人数上限");
                    return ret;
                } else {
                    if (member.getBalance()<(venues.getAllprice())){
                        ret.put("type","error");
                        ret.put("msg","余额不足请充值");
                        return ret;
                    }else {
                        booking.setPrice(price);
                        balance = member.getBalance()-price;
                        memberService.editmember(balance, userid);
                        booking.setQuote(2);
                        bookingService.add(booking);

                    }
                }
            }
        }else if(booking.getQuote()==1){
            ret.put("type","error");
            ret.put("msg","有比赛不可预约");
            return ret;
        }else if(booking.getQuote()==2){
            if (booking.getOrdertypeid()==1) {
                ret.put("type","error");
                ret.put("msg","场地不空不可预约比赛");
                return ret;
            }else{
                if (booking.getSum()+booking.getNumber()>venues.getMax()) {
                    ret.put("type","error");
                    ret.put("msg","超出球场人数上限");
                    return ret;
                } else {
                    if (member.getBalance()<(venues.getPrice()*booking.getNumber())){
                        ret.put("type","error");
                        ret.put("msg","余额不足请充值");
                        return ret;
                    }else {
                        booking.setPrice(price);
                        balance = member.getBalance()-price;
                        memberService.editmember(balance, userid);
                        booking.setQuote(2);
                        bookingService.add(booking);
                    }
                }
            }
        }
        ret.put("type", "success");
        ret.put("msg", "预约成功！");
        return ret;
    }
}
