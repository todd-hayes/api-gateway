package com.thayes.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	public CommandLineRunner commandLineRunner(String[] args) {

		return runner -> {
			System.out.println("Hello World");
		};
	}
}
