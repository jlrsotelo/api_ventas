package com.ventas.app.security.constants;

public interface SecurityConstant {
	
		//Spring Security
	
		String API_AUTH="/api/v1/auth"; 

		String LOGIN_URL = "/login";
		
		String LOGIN_BODY_URL = "/login/body";
		
		//String TOKEN_REFRESH_URL = "/tokenRefresh";

		String HEADER_AUTHORIZACION_KEY = "Authorization";

		String TOKEN_BEARER_PREFIX = "Bearer ";

		String AUTHORITIES = "authorities";

		// JWT
		String ISSUER_INFO = "http://www.galaxy.edu.pe/";

		// https://www.allkeysgenerator.com/ Encryption key 512-bit

		String SUPER_SECRET_KEY = "5cd608d85d0a64c97f297432a0ef79a725985ef48a69f920628993271f486afc"; 


		//long TOKEN_EXPIRATION_TIME = 86_400_000; // 1 day 86_400_000 // Milisegundos
		
		long TOKEN_EXPIRATION_TIME = 900_000; // 1 day 86_400_000 // Milisegundos - 3 Minutos 1_000*60*60 => 3_600_000

		//long TOKEN_REFRESH_EXPIRATION_TIME = 360_000; // 1 day 86_400_000 // Milisegundos

		//String USER_ID="user_id";
		
		//String ORGANIZATION_ID="organization_id";
		
		//String USER_TYPE="user_type";

}
