package com.ventas.app.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		String id = "gtcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("gtcrypt", GalaxyPasswordEncoder.getInstance()); // Lagacy
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("argon2", new Argon2PasswordEncoder( 16,32,1,1 << 14,2));

		return new DelegatingPasswordEncoder(id, encoders);
	}
}
