package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Stadium;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/stadium")
@Controller
@ResponseBody
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){
        mv.setViewName("stadium/list");
        return mv;
    }
    //获取球馆列表
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String, Object> getList(Page page,
                                       @RequestParam(name="name",required=false,defaultValue="") String stadiumname
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("stadiumname", stadiumname);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", stadiumService.findList(queryMap));
        ret.put("total", stadiumService.getTotal(queryMap));
        return ret;
    }
    //添加球馆
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, String> add(Stadium stadium){
        Map<String, String> ret = new HashMap<String, String>();
        if(stadiumService.add(stadium) <= 0 ){
            ret.put("type", "error");ret.put("msg", "球馆添加失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "球馆添加成功！");return ret;
    }
    //编辑球馆，具体步骤进行折叠
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public Map<String, String> edit(Stadium stadium){
        Map<String, String> ret = new HashMap<String, String>();
        if(stadiumService.edit(stadium) <= 0){
            ret.put("type", "error");ret.put("msg", "球馆失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "球馆添加成功！");return ret;
    }
    //批量删除球馆
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Map<String, String> delete(String ids){
        Map<String, String> ret = new HashMap<String, String>();
        //校验数据，具体步骤进行折叠
        if(StringUtils.isEmpty(ids)){
            ret.put("type", "error");
            ret.put("msg", "选择要删除的数据！");
            return ret;
        }
        if(ids.contains(",")){
            ids = ids.substring(0,ids.length()-1);
        }
        if(stadiumService.findvenues(ids)> 0 ){
            ret.put("type", "error");ret.put("msg", "球馆下仍有球场未删除");return ret;
        }
        if(stadiumService.delete(ids) <= 0){
            ret.put("type", "error");ret.put("msg", "球馆删除失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "球馆删除成功！");return ret;
    }
}
