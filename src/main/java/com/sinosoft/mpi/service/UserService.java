package com.sinosoft.mpi.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 登录用户相关信息
 */
@Component
public class UserService {

	/**
	 * 获取登录用户名
	 */
	public String getLoginUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication == null ? null : authentication.getName();
	}

}
