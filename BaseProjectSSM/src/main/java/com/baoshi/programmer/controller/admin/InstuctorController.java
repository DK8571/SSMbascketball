package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Cashier;
import com.baoshi.programmer.entity.admin.Instuctor;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.CashierService;
import com.baoshi.programmer.service.admin.InstuctorService;
import com.baoshi.programmer.service.admin.StadiumService;
import com.baoshi.programmer.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/instuctor")
@ResponseBody
public class InstuctorController {

    @Autowired
    private InstuctorService instuctorService;
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private UserService userService;
    @Autowired
    private CashierService cashierService;
    //跳转页面
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request,ModelAndView model) {
        Map<String,Object> queryMap=new HashMap<String,Object>();
        //获取用户角色
        model.addObject("stadiumList", stadiumService.findList(queryMap));
        User user = (User) request.getSession().getAttribute("admin");
        //根据角色跳转
        if(user.getRoleId()==2){model.setViewName("cashierinstuctor/list");}
        else{model.setViewName("instuctor/list");}
        return model;
    }
    //获取教练列表，具体步骤进行折叠
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String, Object> getList(Page page, HttpServletRequest request,
                                       @RequestParam(name = "username",required = false,defaultValue = "") String instuctorname,
                                       @RequestParam(name = "sex",required = false) Integer sex,
                                       @RequestParam(name = "stadiumId",required = false) Long stadiumId
    ) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        User user = userService.findbyuserid(userid);
        //获取用户id
        queryMap.put("roleId" , user.getRoleId());
        //获取角色id
        queryMap.put("cashierid" , user.getId());
        queryMap.put("instuctorname", instuctorname);queryMap.put("sex",sex);
        queryMap.put("stadiumId",stadiumId);queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", instuctorService.findList(queryMap));
        resultMap.put("total", instuctorService.getTotal(queryMap));
        return resultMap;
    }
    //添加教练
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Map<String, String> add(HttpServletRequest request,Instuctor instuctor){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("admin");
        //收银员添加教练操作
        if(user.getRoleId()==2){
            //获取收银员所属球馆id，赋给球场
            queryMap.put("username", user.getUsername());
            List<User> cashiers = cashierService.findList(queryMap);
            instuctor.setStadiumid(Long.valueOf(cashiers.get(0).getStadiumid()));
        }
        Map<String, String> ret = new HashMap<String, String>();
        //校验数据，具体步骤进行折叠
        if(instuctor == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的教练信息！");
            return ret;
        }//教练不为空
        if(StringUtils.isEmpty(instuctor.getName())){
            ret.put("type", "error");ret.put("msg", "请填写教练姓名！");return ret;
        }
        if(instuctorService.add(instuctor) <= 0 ){
            ret.put("type", "error");ret.put("msg", "教练添加失败，请联系管理员！");return ret;
        }
        ret.put("type", "success");ret.put("msg", "教练添加成功！");return ret;
    }
    //编辑教练，具体步骤进行折叠
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public Map<String, String> edit(HttpServletRequest request,Instuctor instuctor){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("admin");
        if(user.getRoleId()==2){
            queryMap.put("username", user.getUsername());
            List<User> cashiers = cashierService.findList(queryMap);
            instuctor.setStadiumid(Long.valueOf(cashiers.get(0).getStadiumid()));
        }
        Map<String, String> ret = new HashMap<String, String>();
        if(instuctor == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的教练信息！");
            return ret;
        }
        if(StringUtils.isEmpty(instuctor.getName())){
            ret.put("type", "error");
            ret.put("msg", "请填教练姓名！");
            return ret;
        }
        if(instuctorService.edit(instuctor) <= 0){
            ret.put("type", "error");
            ret.put("msg", "教练信息修改失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "教练信息修改成功！");
        return ret;
    }
    //批量删除教练，具体步骤进行折叠
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

        if(instuctorService.delete(ids) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户删除失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "用户删除成功！");
        return ret;
    }

    /**
     * 上传图片
     * @param photo
     * @param request
     * @return
     */
    @RequestMapping(value="/upload_photo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        if(photo == null){
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if(photo.getSize() > 1024*1024*1024){
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
        if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        String savePath = request.getSession().getServletContext().getRealPath("/") + "/resources/upload/";
        File savePathFile = new File(savePath);
        if(!savePathFile.exists()){
            //若不存在改目录，则创建目录
            savePathFile.mkdir();
        }
        String filename = new Date().getTime()+"."+suffix;
        try {
            //将文件保存至指定目录
            photo.transferTo(new File(savePath+filename));
        }catch (Exception e) {
            // TODO Auto-generated catch block
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "用户删除成功！");
        ret.put("filepath",request.getSession().getServletContext().getContextPath() + "/resources/upload/" + filename );
        return ret;
    }
}
