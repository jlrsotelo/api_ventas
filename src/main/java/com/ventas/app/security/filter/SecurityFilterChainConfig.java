package com.ventas.app.security.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.ventas.app.security.provider.CustomAuthenticationProvider;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity
@Slf4j
@Configuration
public class SecurityFilterChainConfig {
	
	private final String PUBLIC_MATCHERS[]= {
			"/public/**"
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
	        .csrf(AbstractHttpConfigurer::disable) //Habilitar POST
	        .httpBasic(withDefaults()); 
	    return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthenticationProvider) {
		return new ProviderManager(customAuthenticationProvider);
	}

}