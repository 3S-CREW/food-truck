package com.toss.foodtruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodTruckBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodTruckBeApplication.class, args);
	}

}
