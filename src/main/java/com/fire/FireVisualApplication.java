package com.fire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fire.app.controller")
public class FireVisualApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireVisualApplication.class, args);
	}
}
