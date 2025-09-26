package com.ventas.app.security.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ventas.app.security.util.Encrypt;

public class GalaxyPasswordEncoder implements PasswordEncoder {
	private static final PasswordEncoder INSTANCE = new GalaxyPasswordEncoder();
	
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }	

	@Override
	public String encode(CharSequence rawPassword) {
		return Encrypt.encrypt(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {    	
    	System.out.println("rawPassword =>"+ rawPassword.toString());
    	System.out.println("encodedPassword =>"+ encodedPassword);
    	
    	System.out.println(rawPassword.toString().compareTo(encodedPassword));
    	
    	return encode(rawPassword).equals(encodedPassword);
	}

}
