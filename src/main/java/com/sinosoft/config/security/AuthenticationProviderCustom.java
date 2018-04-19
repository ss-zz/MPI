package com.sinosoft.config.security;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.sinosoft.mpi.service.SysUserService;

/**
 * 自定义认证
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月9日
 */
@Component
public class AuthenticationProviderCustom implements AuthenticationProvider {

	@Resource
	SysUserService userServcie;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	// session名-上次登录信息
	public static final String SESSION_NAME_LASTLOGINNAME = "SESSION_NAME_LASTLOGINNAME";
	public static final String SESSION_NAME_LASTLOGINPWD = "SESSION_NAME_LASTLOGINPWD";

	// cookie名-上次登录信息
	public static final String COOKIE_NAME_LASTLOGINNAME = "COOKIE_NAME_LASTLOGINNAME";

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		HttpSession session = request.getSession();

		// 用户名
		String userName = authentication.getName();
		// 密码
		String passWord = String.valueOf(authentication.getCredentials());
		// 记录session
		session.setAttribute(SESSION_NAME_LASTLOGINNAME, userName);
		session.setAttribute(SESSION_NAME_LASTLOGINPWD, passWord);

		// 验证验证码
		String requestCaptcha = request.getParameter("yzm");
		Object securityCodeObj = request.getSession().getAttribute("securityCode");
		String genCaptcha = securityCodeObj == null ? null : securityCodeObj.toString();

		if (requestCaptcha == null) {
			throw new BadCredentialsException("请输入验证码");
		}
		if (!requestCaptcha.equals(genCaptcha)) {
			throw new BadCredentialsException("验证码错误");
		}

		// 验证用户名、密码
		if (userServcie.auth(userName, passWord)) {

			// 授权
			Collection<? extends GrantedAuthority> authorities = null;

			// 记录cookie
			Cookie cookie = new Cookie(COOKIE_NAME_LASTLOGINNAME, userName);
			cookie.setMaxAge(2592000);// 30天
			response.addCookie(cookie);

			return new UsernamePasswordAuthenticationToken(userName, passWord, authorities);
		} else {
			session.setAttribute(SESSION_NAME_LASTLOGINPWD, null);
			throw new BadCredentialsException("用户名或密码错误");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
