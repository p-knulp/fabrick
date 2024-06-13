package com.fabrick.asteroidspath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsteroidspathApplication {

	public static void main(String[] args) {
		System.out.println("--> start del servizio asteroids che esopone edpoint d'interrogazione <--");
		SpringApplication.run(AsteroidspathApplication.class, args);
	}

}
