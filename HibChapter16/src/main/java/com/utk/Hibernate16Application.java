package com.utk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.utk.entity.Auction;
import com.utk.entity.User;
import com.utk.util.CsvDataLoader;
import com.utk.util.UserRepository;

@SpringBootApplication
@Import(CsvDataLoader.class)
public class Hibernate16Application {

	@Autowired
	private Auction auction;

	public static void main(String[] args) {
		SpringApplication.run(Hibernate16Application.class, args);
	}

	@Bean
	ApplicationRunner configureRepository(UserRepository userRepository) {
		return env -> {
			for (User user : auction.getUsers()) {
				userRepository.save(user);
			}
		};
	}
}
