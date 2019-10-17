package com.cw.cms.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cw.cms.core.CMSRuntimeException;
import com.cw.cms.domain.User;
import com.cw.cms.service.UserInfoService;
import com.cw.cms.web.Constant;
import com.cw.cms.web.forms.UserForm;

@Controller
public class UserInfoController {
	
	@Autowired
	private UserInfoService UserInfoService;
	
	//跳转注册页面
	@RequestMapping(value="/reg",method=RequestMethod.GET)
	public Object reg(Model model){
		model.addAttribute("userForm",new UserForm());
		
		return "passport/reg";
	}
	
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	public Object regInfo(Model model,UserForm userForm,RedirectAttributes attr){
		try {
			int i = UserInfoService.reg(userForm);
			attr.addFlashAttribute("massage","注册成功");
			
			return "redirect:login";
			
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		
		return "passport/reg";
	}
	
	//跳转登录页面
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public Object login(Model model){
		model.addAttribute("userForm",new UserForm());
		
		return "passport/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Object login(Model model,UserForm userForm,HttpServletRequest request){
		//获取UserForm对象
		model.addAttribute("userform",new UserForm());
		//将user对象放到session中
		try {
			User user = UserInfoService.login(userForm);
			Constant.setLoginUser(request, user);
			return "redirect:home";
			
		} catch (Exception e) { 			
			model.addAttribute("message", e.getMessage());
			
		}
		return "passport/login";
	}
	
	//注销
	@RequestMapping("/logout")
	public Object logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:home";
	}
}
