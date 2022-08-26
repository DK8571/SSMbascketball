package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/recharge")
@ResponseBody
public class RechargeController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){

        mv.setViewName("recharge/list");

        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page,
                                   HttpServletRequest request
                                   ){
        Map<String,Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("userid",userid);
        ret.put("rows", memberService.findList(queryMap));
        ret.put("total", memberService.getTotal(queryMap));
        return ret;
    }
}
