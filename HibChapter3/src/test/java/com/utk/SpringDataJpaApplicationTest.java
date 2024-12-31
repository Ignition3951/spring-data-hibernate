package com.utk;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.utk.entity.User;
import com.utk.repository.UserRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class SpringDataJpaApplicationTest {

	@Autowired
	UserRepository userRepository;

	@BeforeAll
	void beforeAll() {
		userRepository.saveAll(generateUsers());
	}

	private static List<User> generateUsers() {
		List<User> users = new ArrayList<>();
		User user1 = new User("Fohny", LocalDate.of(2024, Month.FEBRUARY, 13));
		user1.setEmail("");
		user1.setActive(true);
		user1.setLevel(2);
		User user2 = new User("Mohh", LocalDate.of(2024, Month.MARCH, 13));
		user2.setEmail("");
		user2.setActive(true);
		user2.setLevel(2);
		User user3 = new User("Aohnl", LocalDate.of(2024, Month.APRIL, 13));
		user3.setEmail("");
		user3.setActive(true);
		user3.setLevel(3);
		User user4 = new User("Mohny", LocalDate.of(2024, Month.MAY, 13));
		user4.setEmail("");
		user4.setActive(true);
		user4.setLevel(3);
		User user5 = new User("Johne", LocalDate.of(2024, Month.JUNE, 13));
		user5.setEmail("");
		user5.setActive(true);
		user5.setLevel(3);
		User user6 = new User("Johny", LocalDate.of(2024, Month.JULY, 13));
		user6.setEmail("sadasdsomeEmail.com");
		user6.setActive(true);
		user6.setLevel(5);
		User user7 = new User("Aohnt", LocalDate.of(2024, Month.AUGUST, 13));
		user7.setEmail("sadasdsomeEmailasad.com");
		user7.setActive(true);
		user7.setLevel(5);
		User user8 = new User("Sohnr", LocalDate.of(2024, Month.SEPTEMBER, 13));
		user8.setEmail("sadasdsomeEmailasd.com");
		user8.setActive(true);
		user8.setLevel(5);
		User user9 = new User("Oohnr", LocalDate.of(2024, Month.OCTOBER, 13));
		user9.setEmail("sadasdsomeEmailvcbvb.com");
		user9.setActive(true);
		user9.setLevel(5);
		User user10 = new User("Utkarsh", LocalDate.of(2024, Month.NOVEMBER, 13));
		user10.setEmail("sadasdsomeEmailzxzx.com");
		user10.setActive(true);
		user10.setLevel(5);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);
		users.add(user10);
		return users;
	}

	@AfterAll
	void afterAll() {
		userRepository.deleteAll();
	}
}
