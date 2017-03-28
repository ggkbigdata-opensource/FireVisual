package com.fire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fire.app.controller")
@ComponentScan("com.fire.app.service")
public class FireVisualApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireVisualApplication.class, args);
	}
}
