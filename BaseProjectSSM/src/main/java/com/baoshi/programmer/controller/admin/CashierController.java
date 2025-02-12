package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.CashierService;
import com.baoshi.programmer.service.admin.StadiumService;
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
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.baoshi.programmer.util.MD5.getMd5;

@RequestMapping("/admin/cashier")
@Controller
public class CashierController {
    @Autowired
    private CashierService cashierService;
    @Autowired
    private StadiumService stadiumService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        Map<String,Object> queryMap = new HashMap<>();
        model.addObject("stadiumList",stadiumService.findList(queryMap));
        model.setViewName("cashier/list");
        return model;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(Page page,
                                        @RequestParam(name = "username",required = false,defaultValue = "") String username,
                                        @RequestParam(name = "sex",required = false) Integer sex,
                                        @RequestParam(name = "satdiumid",required = false) Integer stadiumid
    ) {
        if(Objects.equals(username,"")){
            username = null;
        }
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", username);
        queryMap.put("sex",sex);
        queryMap.put("satdiumid",stadiumid);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", cashierService.findList(queryMap));
        resultMap.put("total", cashierService.getTotal(queryMap));
        return resultMap;
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(User user) throws NoSuchAlgorithmException {
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
        if(StringUtils.isEmpty(user.getPassword())){
            ret.put("type", "error");
            ret.put("msg", "请填写密码！");
            return ret;
        }
        if(isExist(user.getUsername(), 0l)){
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在，请重新输入！");
            return ret;
        }
        user.setPassword(getMd5(user.getPassword()));
        if(cashierService.add(user) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户添加失败，请联系管理员！");
            return ret;
        }
        cashierService.addcashier(user.getId(),user.getStadiumid());
        ret.put("type", "success");
        ret.put("msg", "角色添加成功！");
        return ret;
    }

    /**
     * ?????
     * @param user
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(User user){
        System.out.println(user);
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
        if(cashierService.edit(user) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "收营员添加成功！");
        return ret;
    }

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
        if(cashierService.delete(ids) <= 0){
            ret.put("type", "error");
            ret.put("msg", "用户删除失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "用户删除成功！");
        return ret;
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
        //?????????
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
        if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        String savePath = request.getSession().getServletContext().getRealPath("/") + "/resources/upload/";
        File savePathFile = new File(savePath);
        if(!savePathFile.exists()){
            //??????????????????
            savePathFile.mkdir();
        }
        String filename = new Date().getTime()+"."+suffix;
        try {
            //????????????????
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
    /**
     * ???????????????
     * @param username
     * @param id
     * @return
     */
    private boolean isExist(String username,Long id){
        User user = cashierService.findByCashiername(username);
        if(user == null)return false;
        if(user.getId().longValue() == id.longValue())return false;
        return true;
    }


}
