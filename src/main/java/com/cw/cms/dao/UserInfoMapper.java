package com.cw.cms.dao;

import com.cw.cms.domain.User;

public interface UserInfoMapper {

	User getUser(String username);

	int getCountByUname(String username);

	int reg(User user);

}
