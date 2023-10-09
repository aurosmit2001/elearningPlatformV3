package com.example.eLearningFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class  ELearningFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningFinalApplication.class, args);
	}

}
 