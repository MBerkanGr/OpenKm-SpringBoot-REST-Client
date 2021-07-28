package com.mehmetberkan.SpringBootOpenKmAPI;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootOpenKmApiApplication {

	@Bean
	public RestTemplate restTemplate() {
		Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("okmAdmin", "admin".toCharArray());
            }
        });
		return new RestTemplate();
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootOpenKmApiApplication.class, args);

		
	}

}
