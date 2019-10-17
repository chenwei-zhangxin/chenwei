package com.cw.cms.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cw.cms.dao.UserInfoMapper;
import com.cw.cms.domain.User;
import com.cw.cms.service.UserInfoService;
import com.cw.cms.utils.AssertUtil;
import com.cw.cms.web.forms.UserForm;
@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper UserInfoMapper;

	@Override
	public User login(UserForm userForm) {
		//当用户名为空
		AssertUtil.hasLength(userForm.getUsername(), "用户名不能为空");
		//当密码为空
		AssertUtil.hasLength(userForm.getPassword(), "密码不能为空");
		
		User user = UserInfoMapper.getUser(userForm.getUsername());
		
		String userform_newpassword = UserInfoService.encrypt(userForm.getUsername(),userForm.getPassword());
		
		AssertUtil.isTrue(user.getPassword().equals(userform_newpassword) ,"密码错误");
		
		User target = new User();
		BeanUtils.copyProperties(user, target,"password");
		
		return target;
	}

	@Override
	public int reg(UserForm userForm) {
		AssertUtil.hasLength(userForm.getUsername(), "用户名不能为空");
		AssertUtil.hasLength(userForm.getRePassword(), "密码不能为空");
		AssertUtil.isTrue(userForm.getPassword().equals(userForm.getRePassword()),"密码不一致");
		
		int i = UserInfoMapper.getCountByUname(userForm.getUsername());
		
		AssertUtil.isTrue(i<=0, "用户名已存在");
		//创建user对象
		User user = new User();
		//设置用户名
		user.setUsername(userForm.getUsername());
		//昵称名设为用户名
		user.setNickname(userForm.getUsername());
		//默认注册用户不会被锁定
		user.setLocked(false);
		//生日为空
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		user.setGender(userForm.getGender());
		
		//密码设置
		user.setPassword(UserInfoService.encrypt(userForm.getUsername(), userForm.getPassword()));
		//添加
		int  j = UserInfoMapper.reg(user);
		
		return j;
	}
	
	
}
