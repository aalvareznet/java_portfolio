package com.uia.ing.soft.olda.dunamys.ing_software_dunamys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class IngSoftwareDunamysApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngSoftwareDunamysApplication.class, args);
	}

}
