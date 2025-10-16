package com.ventas.app.security.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ventas.app.security.entity.UserEntity;
import com.ventas.app.security.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements  UserDetailsService{
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity= userRepository.loadUserByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(String.format("User not found => %s",username)));
		log.info(userEntity.toString());
		return CustomUser.create(userEntity);
	}
}
