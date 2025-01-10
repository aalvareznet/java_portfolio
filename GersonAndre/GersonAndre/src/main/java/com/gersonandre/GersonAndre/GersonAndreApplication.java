package com.gersonandre.GersonAndre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class GersonAndreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GersonAndreApplication.class, args);

	}

}
