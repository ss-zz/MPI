package com.sinosoft.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 自定义web认证
	 */
	@Autowired
	private AuthenticationProviderCustom authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 禁止srping控制cache control
		http.headers().cacheControl().disable();

		// 允许iframe显示同源页面
		http.headers().frameOptions().sameOrigin();

		http.csrf().disable()// 禁用csrf
				.authorizeRequests()// 安全认证
				.antMatchers("/ws/**", "/login/**").permitAll()// 放过的地址
				.anyRequest().authenticated() // 其它
				.and().formLogin()// 登录页
				.loginPage("/login").defaultSuccessUrl("/index", true).permitAll().and().logout()// 登出页
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}

	/**
	 * 认证配置
	 *
	 *
	 * @param auth
	 * @throws Exception
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 自定义认证
		auth.authenticationProvider(authProvider);
	}

}
