package com.riwi.artemisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class ArtemisaEcommerceApiApplication {

	public static void main(String[] args) {
		System.out.println("JWT_SECRET from environment: " + System.getenv("JWT_SECRET"));
		SpringApplication.run(ArtemisaEcommerceApiApplication.class, args);
		
	}


}
