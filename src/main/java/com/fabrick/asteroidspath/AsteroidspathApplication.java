package com.fabrick.asteroidspath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe di main start service
 */
@SpringBootApplication
@EnableSwagger2
public class AsteroidspathApplication {
	public static void main(String[] args) {
		System.out.println("--> Start del servizio asteroids che espone endpoint d'interrogazione <--");
		SpringApplication.run(AsteroidspathApplication.class, args);
	}
}
