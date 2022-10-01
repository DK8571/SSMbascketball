package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.Member;
import com.baoshi.programmer.entity.admin.User;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.MemberService;
import com.baoshi.programmer.service.admin.RoleService;
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

import static com.baoshi.programmer.util.MD5.getMd5;

/**
 * 用户管理控制器
 * @author drg
 *
 */
@RequestMapping("/admin/cashierrecharge")
@Controller
@ResponseBody
public class CashierrechargeController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleService roleService;

    //会员列表页面
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String, Object> queryMap = new HashMap<String, Object>();
        model.setViewName("cashierrecharge/list");
        return model;
    }

    //获取会员列表
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public Map<String, Object> getList(Page page,
                                       @RequestParam(name="name",required=false,defaultValue="") String username,
                                       @RequestParam(name="sex",required=false) Integer sex
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", username);
        queryMap.put("sex", sex);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", memberService.findList(queryMap));
        ret.put("total", memberService.getTotal(queryMap));
        return ret;
    }
    //充值
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public Map<String, String> edit(String balance,Long id,HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        //获取会员id
        Member member = memberService.findbyuserid(id);
        //完成充值
        member.setBalance(member.getBalance() + Double.parseDouble(balance));
        memberService.editmember(member.getBalance(),id);
        ret.put("type", "success");ret.put("msg", "充值成功！");return ret;
    }
}
