package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Instuctor;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.page.admin.Page;
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

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        Map<String,Object> queryMap=new HashMap<String,Object>();
        model.addObject("stadiumList", stadiumService.findList(queryMap));
        model.setViewName("instuctor/list");
        return model;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(Page page,
                                       HttpServletRequest request,
                                       @RequestParam(name = "name",required = false,defaultValue = "") String instuctorname,
                                       @RequestParam(name = "sex",required = false) Integer sex,
                                       @RequestParam(name = "stadiumId",required = false) Long stadiumId
    ) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        User user = userService.findbyuserid(userid);
        queryMap.put("roleId" , user.getRoleId());
        queryMap.put("cashierid" , user.getId());
        queryMap.put("instuctorname", instuctorname);
        queryMap.put("sex",sex);
        queryMap.put("stadiumId",stadiumId);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", instuctorService.findList(queryMap));
        resultMap.put("total", instuctorService.getTotal(queryMap));
        return resultMap;
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Instuctor instuctor){
        Map<String, String> ret = new HashMap<String, String>();
        if(instuctor == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的教练信息！");
            return ret;
        }
        if(StringUtils.isEmpty(instuctor.getName())){
            ret.put("type", "error");
            ret.put("msg", "请填写教练姓名！");
            return ret;
        }
        if(instuctorService.add(instuctor) <= 0 ){

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
    public Map<String, String> edit(Instuctor instuctor){
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
