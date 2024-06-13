package com.fabrick.asteroidspath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AsteroidspathApplication {

	public static void main(String[] args) {
		System.out.println("--> start del servizio asteroids che esopone edpoint d'interrogazione <--");
		SpringApplication.run(AsteroidspathApplication.class, args);
	}

}
