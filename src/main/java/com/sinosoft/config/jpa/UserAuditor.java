package com.sinosoft.config.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * spring data jpa CreatedBy LastModifiedBy注解生效配置
 * 
 * @author sinosoft
 *
 */
public class UserAuditor implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		// 从spring security中获取当前用户信息
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx == null) {
			return null;
		}
		if (ctx.getAuthentication() == null) {
			return null;
		}
		if (ctx.getAuthentication().getPrincipal() == null) {
			return null;
		}
		Object principal = ctx.getAuthentication().getPrincipal();
		if (principal.getClass().isAssignableFrom(String.class)) {
			return (String) principal;
		} else {
			return null;
		}

	}

}
