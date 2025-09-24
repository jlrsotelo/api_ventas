package com.ventas.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		log.info("defaultSecurityFilterChain...");
	    http
	        .authorizeHttpRequests((requests) -> requests
	            .requestMatchers("/api/v1/categoria/*").authenticated()
	            .requestMatchers("api/v1/producto/*").permitAll())
	        //.formLogin(withDefaults()) 
	        .httpBasic(withDefaults()); 
	    return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		log.info("userDetailsService...");
		UserDetails admin = User.builder()
			.username("admin")
			//.password("{noop}adminpass")
			.password(passwordEncoder.encode("adminpass"))
			.roles("ADMIN", "USER")
			.build();
		UserDetails user = User.builder() 
			.username("user")
			//.password("{noop}password")
			.password(passwordEncoder.encode("password"))
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}