package com.ventas.app.security.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableMethodSecurity
@Slf4j
@Configuration
public class SecurityFilterChainConfig {
	
	private final String PUBLIC_MATCHERS[]= {
			"/public/**",
			"/api/v1/auth/**",
			"/api/v1/users/**",
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html"
	};
	
	private final String PRIVATE_CONSULTA_MATCHERS[]= {
			"/private/api/v1/categoria/consulta/**",
			"/private/api/v1/producto/consulta/**",
			"/private/api/v1/cliente/consulta/**"
	};
	
	private final String PRIVATE_GESTION_MATCHERS[]= {
			"/private/api/v1/categoria/gestion/**",
			"/private/api/v1/producto/gestion/**",
			"/private/api/v1/cliente/gestion/**"
	};
	
	private final SecurityTokenFilter securityTokenFilter;
	
	public SecurityFilterChainConfig(SecurityTokenFilter securityTokenFilter) {
		super();
		this.securityTokenFilter = securityTokenFilter;
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		log.info("defaultSecurityFilterChain...");
	    http
	        .authorizeHttpRequests(
	        	(requests) -> requests
	        	.requestMatchers(PUBLIC_MATCHERS).permitAll()
	            .requestMatchers(PRIVATE_CONSULTA_MATCHERS).hasAnyRole("USER")
	            .requestMatchers(PRIVATE_GESTION_MATCHERS).hasAnyRole("ADMIN","SUPER")
	            .anyRequest()
	            .authenticated()
	         )
	        .csrf(AbstractHttpConfigurer::disable);
	        http.addFilterBefore(securityTokenFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}
	
	 @Bean
	 AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	 }
}