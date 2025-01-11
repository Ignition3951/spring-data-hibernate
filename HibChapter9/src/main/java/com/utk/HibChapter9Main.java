package com.utk;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.utk.service.TestService;

@SpringBootApplication
public class HibChapter9Main {

	public static void main(String[] args) {
		SpringApplication.run(HibChapter9Main.class, args);
	}

	@Bean
	public ApplicationRunner configure(TestService testService) {
		return env -> {
			testService.storeLoadEntities();
		};
	}
}
