package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.service.admin.MemberService;
import com.baoshi.programmer.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/admin/ownmasage")
public class OwnmasageController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleService roleService;

    //获取个人信息
    @RequestMapping(value = "list",method = RequestMethod.GET)
    ModelAndView list(ModelAndView mv,HttpServletRequest request){
        mv.setViewName("ownmasage/ownmasage");
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("userid",request.getSession().getAttribute("adminid"));
        mv.addObject("Member",memberService.findList(queryMap));
        return mv;
    }
    //修改个人信息
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public Map<String, String> edit(User user){
        Map<String, String> ret = new HashMap<String, String>();
        if(user == null){
            ret.put("type", "error");
            ret.put("msg", "请填写正确的用户信息！");
            return ret;
        }
        if(StringUtils.isEmpty(user.getUsername())){
            ret.put("type", "error");
            ret.put("msg", "请填写用户名！");
            return ret;
        }
        if(isExist(user.getUsername(), user.getId())){
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在，请重新输入！");
            return ret;
        }
        if(memberService.edit(user) <= 0){
            ret.put("type", "error");ret.put("msg", "用户添加失败，请联系管理员！");return ret;
        }
        memberService.editmember(user.getBalance(), user.getId());
        ret.put("type", "success");ret.put("msg", "角色添加成功！");return ret;
    }

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
    private boolean isExist(String username,Long id){
        User user = memberService.findByUsername(username);
        if(user == null)return false;
        if(user.getId().longValue() == id.longValue())return false;
        return true;
    }
}
