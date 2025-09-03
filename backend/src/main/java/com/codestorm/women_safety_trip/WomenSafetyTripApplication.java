package com.codestorm.women_safety_trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class WomenSafetyTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(WomenSafetyTripApplication.class, args);
	}

}
