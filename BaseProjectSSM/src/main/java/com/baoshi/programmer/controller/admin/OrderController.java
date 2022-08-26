package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin/order")
@ResponseBody
public class OrderController {
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
        mv.addObject("memberlist",memberService.findList(queryMap));
        mv.addObject("venuesList", venuesService.findList(queryMap));
        mv.setViewName("order/list");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page,
                                   @RequestParam(value = "datestr",required = false) String date,
                                   @RequestParam(value = "ordertypeid",required = false) Integer ordertypeid,
                                   @RequestParam(value = "memberid",required = false) Integer memberid,
                                   @RequestParam(value = "venuesid",required = false) Integer venuesid,
                                   @RequestParam(value = "timeid",required = false) Integer timeid
                                   ){
        Map<String,Object> queryMap = new HashMap<>();
        if (Objects.equals(date,"")){
            date=null;
        }
        queryMap.put("date",date);
        queryMap.put("ordertypeid",ordertypeid);
        queryMap.put("memberid",memberid);
        queryMap.put("venuesid",venuesid);
        queryMap.put("timeid",timeid);
        Map<String,Object> ret = new HashMap<>();
        ret.put("rows",orderService.findList(queryMap));

        ret.put("total",orderService.getTotal(queryMap));
        return ret;
    }
}
