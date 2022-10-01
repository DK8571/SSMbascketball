package com.baoshi.programmer.controller.admin;


import com.baoshi.programmer.entity.admin.Equipment;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.EquipmentService;
import com.baoshi.programmer.service.admin.UserService;
import com.baoshi.programmer.service.admin.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/equipment")
@ResponseBody
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private VenuesService venuesService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv, HttpServletRequest request){
        Map<String,Object> queryMap=new HashMap<String,Object>();
        //获取登录用户id与角色id
        Long userid = (Long) request.getSession().getAttribute("adminid");
        User user = userService.findbyuserid(userid);
        queryMap.put("roleId" , user.getRoleId());
        queryMap.put("cashierid" , user.getId());
        mv.addObject("venuesList", venuesService.findListbycashierid(queryMap));
        mv.addObject("userList", userService.findListbycashierid(queryMap));
        mv.setViewName("equipment/list");
        return mv;
    }
    //获取设备列表，具体步骤进行折叠
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Map<String,Object> getlist(Page page, HttpServletRequest request,
                                      @RequestParam(value = "equipmentname",required = false) String equipmentname,
                                      @RequestParam(value = "userid",required = false) Integer userid,
                                      @RequestParam(value = "venuesid",required = false) Integer venuesid)
    {
        Map<String,Object> queryMap=new HashMap<String,Object>();
        //获取调用功能用户
        Long userId = (Long) request.getSession().getAttribute("adminid");
        User user = userService.findbyuserid(userId);
        //角色id和用户id搜索设备
        queryMap.put("roleId" , user.getRoleId());
        queryMap.put("cashierid" , user.getId());
        queryMap.put("equipmentname",equipmentname);
        queryMap.put("userid",userid);
        queryMap.put("venuesid",venuesid);
        request.getSession().setAttribute("venuesid",venuesid);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("venuesi",venuesid);
        resultMap.put("rows",equipmentService.findList(queryMap));
        resultMap.put("total",equipmentService.getTotal(queryMap));
        return resultMap;
    }
    //添加设备
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, String> add(HttpServletRequest request,Equipment equipment){
        //填入此操作人id
        equipment.setUserid((Long) request.getSession().getAttribute("adminid"));
        Map<String, String> ret = new HashMap<String, String>();
        //校验数据，具体步骤进行折叠
        if(equipment == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的用户信息！");
            return ret;
        }//填写正确设备信息
        if(StringUtils.isEmpty(equipment.getEquipmentname())){
            ret.put("type", "error");
            ret.put("msg", "请填写设备！");
            return ret;
        }//填写设备名称
        if(equipment.getVenuesid() == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属球场！");
            return ret;
        }//填写所属球场id
        if(equipmentService.add(equipment) <= 0){
            ret.put("type", "error");ret.put("msg", "设备添加失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "设备添加成功！");return ret;
    }
    //编辑设备
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    //前台传入操作人用户id
    public Map<String, String> edit(Equipment equipment){
        Map<String, String> ret = new HashMap<String, String>();
        //校验数据，具体步骤进行折叠
        if(equipment == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的设备信息！");
            return ret;
        }//填写正确设备信息
        if(StringUtils.isEmpty(equipment.getEquipmentname())){
            ret.put("type", "error");
            ret.put("msg", "请填写设备名！");
            return ret;
        }//填写设备名称
        if(equipment.getEquipmentname() == null){
            ret.put("type", "error");
            ret.put("msg", "请选择所属球场！");
            return ret;
        }//填写所属球场id
        if(equipmentService.edit(equipment) <= 0){
            ret.put("type", "error");ret.put("msg", "设备编辑失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "设备添加成功！");return ret;
    }
    //批量删除设备
    @RequestMapping(value="/delete",method=RequestMethod.POST)
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
        if(equipmentService.delete(ids) <= 0){
            ret.put("type", "error");
            ret.put("msg", "设备删除失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "设备删除成功！");
        return ret;
    }

    private boolean isExist(String equipmentname,Long id){
        Equipment equipment = equipmentService.findByequipmentname(equipmentname);
        if(equipment == null)return false;
        if(equipment.getId().longValue() == id.longValue())return false;
        return true;
    }
}
