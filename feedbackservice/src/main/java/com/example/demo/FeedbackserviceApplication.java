package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class FeedbackserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackserviceApplication.class, args);
	}

}
