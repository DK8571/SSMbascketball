package com.baoshi.programmer.controller.admin;


import com.baoshi.programmer.entity.admin.Venues;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.StadiumService;
import com.baoshi.programmer.service.admin.VenuesService;
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

@Controller
@RequestMapping("/admin/venues")
@ResponseBody
public class VenuesController {

    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private VenuesService venuesService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){
        Map<String,Object> queryMap=new HashMap<String,Object>();
        mv.addObject("stadiumList", stadiumService.findList(queryMap));
        mv.setViewName("venues/list");
        return mv;
    }
    //获取球场列表
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> getList(Page page,
                                      @RequestParam(value = "venuesname",required = false) String venuesname,
                                      @RequestParam(value = "stadiumId",required = false) Integer stadiumId){
        Map<String,Object> queryMap=new HashMap<String,Object>();
        queryMap.put("stadiumId",stadiumId);
        queryMap.put("venuesname",venuesname);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("rows",venuesService.findList(queryMap));
        resultMap.put("total",venuesService.getTotal(queryMap));
        return resultMap;
    }
    //添加球场
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, String> add(Venues venues){
        Map<String, String> ret = new HashMap<String, String>();
        //校验数据，具体步骤进行折叠
        if(venues == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的用户信息！");
            return ret;
        }
        if(StringUtils.isEmpty(venues.getVenuesname())){
            ret.put("type", "error");
            ret.put("msg", "请填写球场编号！");
            return ret;
        }//校验球场编号
        if(venues.getStadiumid() == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属球馆！");
            return ret;
        }//校验球馆id
        if(venues.getPrice() == null){
            ret.put("type", "error");
            ret.put("msg", "请添加单价");
            return ret;
        }//校验单价
        if(venues.getAllprice() == null){
            ret.put("type", "error");
            ret.put("msg", "请添加包场价格");
            return ret;
        }//校验全场价格
        if(venues.getMax() == 0){
            ret.put("type", "error");
            ret.put("msg", "请添加人数上限");
            return ret;
        }//最大人数
        if(venuesService.add(venues) <= 0){
            ret.put("type", "error");ret.put("msg", "球场添加失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "球场添加成功！");return ret;
    }
    //编辑球场
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public Map<String, String> edit(Venues venues){
        Map<String, String> ret = new HashMap<String, String>();
        if(venues == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的用户信息！");
            return ret;
        }
        if(StringUtils.isEmpty(venues.getVenuesname())){
            ret.put("type", "error");
            ret.put("msg", "请填写用户名！");
            return ret;
        }
        if(venues.getStadiumid() == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属球馆！");
            return ret;
        }
        if(venues.getPrice() == null){
            ret.put("type", "error");
            ret.put("msg", "请添加单价");
            return ret;
        }
        if(venues.getAllprice() == null){
            ret.put("type", "error");
            ret.put("msg", "请添加包场价格");
            return ret;
        }
        if(venues.getMax() == null){
            ret.put("type", "error");
            ret.put("msg", "请添加人数上限");
            return ret;
        }
        if(venuesService.edit(venues) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "角色添加成功！");
        return ret;
    }
    //批量删除球场
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public Map<String, String> delete(String ids){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(ids)){
            ret.put("type", "error");ret.put("msg", "选择要删除的数据！");return ret;}
        if(ids.contains(",")){
            ids = ids.substring(0,ids.length()-1);}
        //球场下是否有设备
        if(venuesService.findequipment(ids)> 0 ){
            ret.put("type", "error");ret.put("msg", "球场下仍有设备未删除");return ret;}
        //球场下是否有订单
        if(venuesService.findeorders(ids)> 0 ){
            ret.put("type", "error");ret.put("msg", "球场下仍有订单未结束");return ret;}
        if(venuesService.delete(ids) <= 0){
            ret.put("type", "error");ret.put("msg", "球场删除失败，请联系管理员！");return ret;}
        ret.put("type", "success");ret.put("msg", "球场删除成功！");return ret;
    }
}
