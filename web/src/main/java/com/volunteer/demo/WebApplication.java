package com.volunteer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "com.volunteer.demo")
public class WebApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
