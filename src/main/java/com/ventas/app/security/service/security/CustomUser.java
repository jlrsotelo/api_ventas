package com.ventas.app.security.service.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.ventas.app.security.entity.UserEntity;

@Slf4j
public class CustomUser extends User implements CustomUserDetails  {

	private static final long serialVersionUID = -5859043141970820746L;
	
	private String id;
	
	private String fullName;

	private String state;


	private CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}
	
	private CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String id, String fullName,String state) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id=id;
		this.fullName=fullName;
		this.state=state;
		
	}
	
	public static CustomUserDetails create(UserEntity userEntity) {
		
		var authorities=userEntity.getAuthorities().stream().map(a-> new SimpleGrantedAuthority(a.getName().toUpperCase())).toList();
		
		CustomUserDetails customUserDetails =	new CustomUser (
				userEntity.getUserName(), //username 
				userEntity.getPassword(),// password, 
				userEntity.getEnabled(), //enabled, 
				userEntity.getAccountNonExpired(),// accountNonExpired, 
				userEntity.getCredentialsNonExpired(),// credentialsNonExpired, 
				userEntity.getAccountNonLocked(),// accountNonLocked, 
				authorities// authorities
				);
		log.info(customUserDetails.toString());
		return customUserDetails;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getFullName() {
		
		return fullName;
	}

	@Override
	public String getState() {
		return state;
	}

}
