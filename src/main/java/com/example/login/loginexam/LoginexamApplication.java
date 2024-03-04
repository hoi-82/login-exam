package com.example.login.loginexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ConfigurationPropertiesScan(value = "com.example.login.loginexam.domain.vo")
public class LoginexamApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginexamApplication.class, args);
	}

}
