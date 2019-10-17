package com.cw.cms.service;

import java.nio.charset.CodingErrorAction;

import com.cw.cms.domain.User;
import com.cw.cms.utils.Codings;
import com.cw.cms.web.forms.UserForm;

public interface UserInfoService {
	//接口中加盐
	static final String SALT="la3h5G8l!9N0vo";
	
	static String encrypt(String username, String password) {
		// TODO Auto-generated method stub
		password = Codings.MD5Encoding(password+UserInfoService.SALT+username);
		
		return password;
	}
	
	//登录
	User login(UserForm userform);

	int reg(UserForm userForm);

	

}
