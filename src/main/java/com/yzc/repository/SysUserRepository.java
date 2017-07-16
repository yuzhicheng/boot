package com.yzc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzc.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, String>{
	
	SysUser findByUsername(String username);

}
