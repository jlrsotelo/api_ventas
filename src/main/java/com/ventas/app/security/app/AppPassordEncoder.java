package com.ventas.app.security.app;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppPassordEncoder {

	public static void main(String[] args) {
		//BCryptPasswordEncoder bCryptPasswordEncoder=	new BCryptPasswordEncoder(10);
		//System.out.println(bCryptPasswordEncoder.encode("123"));
		
		//Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
		
		Argon2PasswordEncoder argon2PasswordEncoder= new Argon2PasswordEncoder( 16, // saltLength (bytes)
	            32, // hashLength (bytes)
	            1,  // parallelism (CPU cost)
	            1 << 14, // memoryCost (KB, 16384KB = 16MB)
	            2   // iterations (time cost)
	            );
		
		System.out.println(argon2PasswordEncoder.encode("123"));
		
		//String psw=passwordEncoder2.encode("password");
		//System.out.println(psw);
		
		/*
		if (psw.equals("$2a$10$WeS.gLQgD035Hsx1dVfz3OBs490XZCTRmoylPjqtVt5H4cQzm0LSq")) {
			System.out.println("ok");
		} else {
			System.out.println("Bad");
		}*/
		
		/*
		if (passwordEncoder2.matches("adminpass","$2a$10$30UhwHU177O0GtL2./B/Xu.QXkTEcwVYT3MIfv0UeVjJU9K77kcvm")) {
			System.out.println("ok");
		} else {
			System.out.println("Bad");
		}*/
		
		

		
	}

}
