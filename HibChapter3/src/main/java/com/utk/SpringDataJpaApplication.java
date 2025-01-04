//package com.utk;
//
//import java.time.LocalDate;
//
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import com.utk.entity.User;
//import com.utk.repository.UserRepository;
//
//@SpringBootApplication
//public class SpringDataJpaApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringDataJpaApplication.class, args);
//	}
//
//	@Bean
//	public ApplicationRunner configure(UserRepository userRepository) {
//		return env -> {
//			User user1 = new User("FirstUser", LocalDate.of(2024, 12, 31));
//			User user2 = new User("SecondUser", LocalDate.of(2024, 12, 01));
//
//			userRepository.save(user1);
//			userRepository.save(user2);
//
//			userRepository.findAll().forEach(System.out::println);
//		};
//
//	}
//
//}
