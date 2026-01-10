package com.springSecurity.hands_on;

import com.springSecurity.hands_on.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableConfigurationProperties(JwtProperties.class)

@SpringBootApplication
public class HandsOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandsOnApplication.class, args);
	}

}
