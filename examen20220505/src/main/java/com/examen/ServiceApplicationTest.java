package com.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplicationTest {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ServiceApplicationTest.class);
		application.run(args);
	}

}
