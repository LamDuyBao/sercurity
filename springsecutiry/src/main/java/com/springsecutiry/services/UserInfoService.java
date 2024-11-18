package com.springsecutiry.services;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springsecutiry.entity.UserInfo;
import com.springsecutiry.entity.UserInfoUserDetails;
import com.springsecutiry.repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService {
	@Autowired
	public UserInfoRepository repository;
	
	public UserInfoService(UserInfoRepository repository) {
		this.repository = repository;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	}
	
}
