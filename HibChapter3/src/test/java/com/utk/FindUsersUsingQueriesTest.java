package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.utk.entity.User;

public class FindUsersUsingQueriesTest extends SpringDataJpaApplicationTest {

	@Test
	void testFindAll() {
		List<User> users = userRepository.findAll();
		assertEquals(12, users.size());
	}

	@Test
	void testFindUser() {
		User beth = userRepository.findByUsername("Aohnl");
		assertEquals("Aohnl", beth.getUsername());
	}

	@Test
	void testFindAllByOrderByUsernameAsc() {
		List<User> users = userRepository.findAllByOrderByUsernameAsc();
		assertAll(() -> assertEquals(12, users.size()), () -> assertEquals("Aohnl", users.get(0).getUsername()),
				() -> assertEquals("Utkarsh", users.get(users.size() - 1).getUsername()));
	}

	@Test
	void testFindByRegistrationDateBetween() {
		List<User> users = userRepository.findByRegistrationDateBetween(LocalDate.of(2024, Month.JULY, 1),
				LocalDate.of(2024, Month.DECEMBER, 31));
		assertEquals(7, users.size());
	}

}
