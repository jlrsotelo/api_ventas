package com.ventas.app.security.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ventas.app.security.entity.UserEntity;
import com.ventas.app.security.repository.UserRepository;

//@Service
public class UserDetailsServiceImpl implements  UserDetailsService{
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity= userRepository.loadUserByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(String.format("User not found => %s",username)));
		
		UserDetails user=User
				.builder()
				.username(userEntity.getUserName())
				.password(userEntity.getPassword())
				.authorities(userEntity.getAuthorities().stream().map(a-> new SimpleGrantedAuthority(a.getName().toUpperCase())).toList())
				.build();
		return user;
	}

}
