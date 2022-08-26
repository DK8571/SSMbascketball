package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/booking")
@ResponseBody
public class BookingController {
    @Autowired
    private StadiumService stadiumService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){
        mv.setViewName("booking/list");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String, Object> getList(Page page,
                                       @RequestParam(name="name",required=false,defaultValue="") String stadiumname
    ){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Map<String, Object> ret = new HashMap<String, Object>();
        queryMap.put("stadiumname", stadiumname);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", stadiumService.findList(queryMap));
        ret.put("total", stadiumService.getTotal(queryMap));
        return ret;
    }
}
