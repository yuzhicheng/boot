package com.yzc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.yzc.service.CustomUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	UserDetailsService customUserService() {

		return new CustomUserService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		 //* Spring Security 默认忽略/css/**、/js/**、/images/**、和/**/favicon.ico 等静态资源的拦截
		//不知道为什么/css/*下的资源还是没有被忽略，需要进行显示配置
		http
		.authorizeRequests()//开始请求权限配置
		.antMatchers("/", "/login").permitAll() //设置Spring Security 对/和/login 路径不进行拦截
		.antMatchers("/bootstrap/**","/jqueryui/**","/**/**.js","/css/**").permitAll() //设置Spring Security 对非默认路径下静态资源不进行拦截
		.anyRequest().authenticated()//其余所有的请求都需要认证后（登录）才可访问
		.and()
		.formLogin()
		       .loginPage("/login") //设置Spring Security 的登录页面访问路径为/login
		       .failureUrl("/login?error")
		       .defaultSuccessUrl("/security")
		       .permitAll()
		.and()
		.logout()
		.permitAll();

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		//在内存中分配两个用户
//		auth
//		.inMemoryAuthentication()
//		.withUser("yzc").password("yzc").roles("USER")
//		.and()
//		.withUser("admin").password("root").roles("ADMIN");
		
		auth.userDetailsService(customUserService());
	}
	
	/**
	 * 不知道为什么这种写法无效
	 */
	@Deprecated
	@Override
	public void configure(WebSecurity web){
		
		//Spring Security 忽略对静态资源的拦截
		web.ignoring().antMatchers("/resources/static/**");
	}

}
