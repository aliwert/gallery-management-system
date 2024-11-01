package com.alimert.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.alimert"})
@ComponentScan(basePackages = {"com.alimert"})
@EnableJpaRepositories(basePackages = {"com.alimert"})
@SpringBootApplication
public class AutoShowroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoShowroomApplication.class, args);
	}

}
