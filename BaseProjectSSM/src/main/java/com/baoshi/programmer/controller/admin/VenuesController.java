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

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, String> add(Venues venues){
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
        if(isExist(venues.getVenuesname(), 0l)){
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在，请重新输入！");
            return ret;
        }
        if(venuesService.add(venues) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "角色添加成功！");
        return ret;
    }

    /**
     * 编辑用户
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
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
//		if(StringUtils.isEmpty(user.getPassword())){
//			ret.put("type", "error");
//			ret.put("msg", "请填写密码！");
//			return ret;
//		}
        if(venues.getStadiumid() == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属球馆！");
            return ret;
        }
        if(isExist(venues.getVenuesname(), venues.getId())){
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在，请重新输入！");
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

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(String ids){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(ids)){
            ret.put("type", "error");
            ret.put("msg", "选择要删除的数据！");
            return ret;
        }
        if(ids.contains(",")){
            ids = ids.substring(0,ids.length()-1);
        }
        if(venuesService.delete(ids) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户删除失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "用户删除成功！");
        return ret;
    }

    private boolean isExist(String venuesname,Long id){
        Venues venues = venuesService.findByVenuesname(venuesname);
        if(venues == null)return false;
        if(venues.getId().longValue() == id.longValue())return false;
        return true;
    }

}
