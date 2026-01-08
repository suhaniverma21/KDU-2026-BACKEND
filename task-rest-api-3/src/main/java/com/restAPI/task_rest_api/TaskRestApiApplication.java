package com.restAPI.task_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry // enables retry logic for @Retryable

public class TaskRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskRestApiApplication.class, args);
	}

}
