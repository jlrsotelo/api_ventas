package com.ventas.app.security.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

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
	
	private final String SQL_USER = """
			select user_name as username, password, state as enabled from
	seg_user where user_name= ? """;

	private final String SQL_AUTHORITIES = """
			select usu.user_name as username, upper(aut.name) as authority from
	seg_authority aut inner join seg_user_authority usa on
	aut.authority_id=usa.authority_id inner join seg_user usu on
	usa.user_id= usu.user_id and usu.user_name= ? """;
	
	private final DataSource datasource;

	public SecurityFilterChainConfig(DataSource datasource) {
		this.datasource = datasource;
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
	        .csrf(AbstractHttpConfigurer::disable) //Habilitar POST
	        .httpBasic(withDefaults()); 
	    return http.build();
	}
	
	@Bean
	JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
		log.info("SQL_USER {}", SQL_USER);
		log.info("SQL_AUTHORITIES {}", SQL_AUTHORITIES);
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		jdbcUserDetailsManager.setUsersByUsernameQuery(SQL_USER);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SQL_AUTHORITIES);
		return jdbcUserDetailsManager;
	}

}