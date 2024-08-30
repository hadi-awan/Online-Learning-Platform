package com.hadi.online_learning_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class OnlineLearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningPlatformApplication.class, args);
	}

}
