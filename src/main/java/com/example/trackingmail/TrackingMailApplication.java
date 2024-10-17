package com.example.trackingmail;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class TrackingMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingMailApplication.class, args);
	}

}
