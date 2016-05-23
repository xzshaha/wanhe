package com.wanhe.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wanhe.controller.BaseController;
import com.wanhe.entity.Admin;
import com.wanhe.service.AdminService;

@Controller
@RequestMapping("/wisdomWeb1/login")
public class LoginBackController extends BaseController {

	@Resource
	private AdminService adminService;

	@RequestMapping(value = "/submit", produces = "application/json; charset=utf-8")
	public ModelAndView submit(String userName, String passWord) {
		ModelAndView mv = new ModelAndView();
		if (!adminService.usernameExists(userName)) {
			mv.addObject("state", "0");
			mv.addObject("msg", "用户没有注册，请注册！");
			mv.setViewName("wisdomWeb/login.jsp");
		}

		Admin admin = adminService.findByUsername(userName);
		passWord = DigestUtils.md5Hex(passWord);
		if (passWord.equals(admin.getPassword())) {
			Admin temp = admin.clone();
			Session session = SecurityUtils.getSubject().getSession(true);
			session.setAttribute(Admin.SESSION_ADMIN, temp);
			mv.addObject("state", "1");
			mv.addObject("msg", "登陆成功！");
			mv.setViewName("wisdomWeb/index.html");
		} else {
			mv.addObject("state", "0");
			mv.addObject("msg", "用户密码不正确！");
			mv.setViewName("wisdomWeb/login.jsp");
		}
		return mv;
	}
}
