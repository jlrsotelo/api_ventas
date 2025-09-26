package com.ventas.app.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CorsConfig {
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				log.info("addCorsMappings...");
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						//.allowedOrigins("https://gray-flower-0851cba1e.1.azurestaticapps.net") 
						.allowedOrigins("http://localhost:4200")
						.allowedHeaders("*");
			}
		};
	}
}
