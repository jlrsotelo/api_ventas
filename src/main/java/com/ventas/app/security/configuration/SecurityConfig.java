package com.ventas.app.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AnnotationTemplateExpressionDefaults;

@Configuration
public class SecurityConfig {
	

	@Bean
	AnnotationTemplateExpressionDefaults annotationTemplateExpressionDefaults(){
		return new AnnotationTemplateExpressionDefaults();
	}

}
