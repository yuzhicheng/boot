package com.yzc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yzc.domain.SysUser;
import com.yzc.repository.SysUserRepository;

public class CustomUserService implements UserDetailsService{
	
	@Autowired
	SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SysUser user=userRepository.findByUsername(username);
		
		if (user==null) {
			throw new  UsernameNotFoundException("用户不存在");
		}
		return user;
	}

}
