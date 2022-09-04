package com.baoshi.programmer.controller.admin;

import com.baoshi.programmer.entity.admin.*;
import com.baoshi.programmer.service.admin.*;
import com.baoshi.programmer.util.CpachaUtil;
import com.baoshi.programmer.util.MenuUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.baoshi.programmer.util.MD5.getMd5;

/**
 * 系统操作类控制器
 * @author drg
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private TurnoverService turnoverService;
	@Autowired
	private MemberService memberService;

//	@Autowired
//	private LogService logService;

	/**
	 * 系统登录后的主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request){
		List<Menu> userMenus = (List<Menu>)request.getSession().getAttribute("userMenus");
		model.addObject("topMenuList", MenuUtil.getAllTopMenu(userMenus));
		model.addObject("secondMenuList", MenuUtil.getAllSecondMenu(userMenus));
		model.setViewName("system/index");
		return model;//WEB-INF/views/+system/index+.jsp = WEB-INF/views/system/index.jsp
	}

	/**
	 * 系统登录后的欢迎页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request
								,ModelAndView model){
		Map<String,Object> querymap = new HashMap<>();
		Role role = (Role) request.getSession().getAttribute("role");
		if (role.getId() == 1){
			querymap.put("id",role.getId());
		}else{
			querymap.put("id",role.getId());
			querymap.put("userid",request.getSession().getAttribute("adminid"));
		}
		List<Turnover> turnovers = turnoverService.findturnover(querymap);
		String date = "";
		String price = "";
		String type;
		for (Turnover tournover:turnovers) {
			date += "'"+tournover.getDatestr()+"'"+",";
			price += tournover.getPrice()+",";
		}
		if(!StringUtils.isEmpty(date)){
			date = date.substring(0,date.length()-1);
		}
		if(!StringUtils.isEmpty(price)){
			price = price.substring(0,price.length()-1);
		};
		if (role.getId()==3){
			type = "消费";
		}else {
			type = "营业额";
		}
		model.addObject("title",type);
		model.addObject("date",date);
		model.addObject("price",price);
		model.setViewName("system/welcome");
		return model;
	}
	/**
	 * 登陆页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("system/login");
		return model;
	}
	@RequestMapping(value="/addlist",method=RequestMethod.GET)
	public ModelAndView addlist(ModelAndView model){
		model.setViewName("system/addlist");
		return model;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User user){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if(org.springframework.util.StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if(org.springframework.util.StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}

		if(isExist(user.getUsername(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		if(memberService.add(user) <= 0 ){

			ret.put("type", "error");
			ret.put("msg", "用户添加失败，请联系管理员！");
			return ret;
		}
		long id = user.getId();
		memberService.addmember(id);
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}
	/**
	 * 登录表单提交处理控制器
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(User user, String cpacha, HttpServletRequest request) throws NoSuchAlgorithmException {
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		if(StringUtils.isEmpty(cpacha)){
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
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
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null){
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
//			logService.add("用户名为"+user.getUsername()+"的用户登录时输入验证码错误!");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername == null){
			ret.put("type", "error");
			ret.put("msg", "该用户名不存在！");
//			logService.add("登录时，用户名为"+user.getUsername()+"的用户不存在!");
			return ret;
		}
		if(!(getMd5(user.getPassword())).equals(findByUsername.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "密码错误！");
//			logService.add("用户名为"+user.getUsername()+"的用户登录时输入密码错误!");
			return ret;
		}
		//说明用户名密码及验证码都正确
		//此时需要查询用户的角色权限
		Role role = roleService.find(findByUsername.getRoleId());
		List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//根据角色获取权限列表
		String menuIds = "";
		for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		//把角色信息、菜单信息放到session中
		request.getSession().setAttribute("admin", findByUsername);
		request.getSession().setAttribute("adminid", findByUsername.getId());
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		ret.put("type", "success");
		ret.put("msg", "登录成功！");
//		logService.add("用户名为{"+user.getUsername()+"}，角色为{"+role.getName()+"}的用户登录成功!");
		return ret;
	}

	/**
	 * 后台退出注销功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
		session.setAttribute("role", null);
		request.getSession().setAttribute("userMenus", null);
		return "redirect:login";
	}

	/**
	 * 修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.GET)
	public ModelAndView editPassword(ModelAndView model){
		model.setViewName("system/edit_password");
		return model;
	}

	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(newpassword)){
			ret.put("type", "error");
			ret.put("msg", "请填写新密码！");
			return ret;
		}
		User user = (User)request.getSession().getAttribute("admin");
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误！");
			return ret;
		}
		user.setPassword(newpassword);
		if(userService.editPassword(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "密码修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功！");
//		logService.add("用户名为{"+user.getUsername()+"}，的用户成功修改密码!");
		return ret;
	}

	/**
	 * 本系统所有的验证码均采用此方法
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param cpachaType:用来区别验证码的类型，传入字符串
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
			@RequestParam(name="w",required=false,defaultValue="100") Integer width,
			@RequestParam(name="h",required=false,defaultValue="30") Integer height,
			@RequestParam(name="type",required=true,defaultValue="loginCpacha") String cpachaType,
			HttpServletRequest request,
			HttpServletResponse response){
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	/**
	 * 判断该用户名是否存在
	 * @param username
	 * @param id
	 * @return
	 */
	private boolean isExist(String username,Long id){
		User user = memberService.findByUsername(username);
		if(user == null)return false;
		if(user.getId().longValue() == id.longValue())return false;
		return true;
	}
}
