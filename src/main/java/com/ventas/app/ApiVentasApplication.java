package com.ventas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
	    title = "API Product Catalog",
	    version = "1.0",
	    description = "API documentation for Product Catalog application"
	)
)

@SpringBootApplication
public class ApiVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVentasApplication.class, args);
	}

}
