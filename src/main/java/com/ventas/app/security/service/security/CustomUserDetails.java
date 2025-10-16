package com.ventas.app.security.service.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails{

	String getId();
	
	String getFullName();
	
	String getState();
}
