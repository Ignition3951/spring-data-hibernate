package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.utk.entity.User;

public class FindUsersSortingAndPagingTest extends SpringDataJpaApplicationTest {

	@Test
	void testOrder() {
		User user1 = userRepository.findFirstByOrderByUsernameAsc();
		User user2 = userRepository.findTopByOrderByRegistrationDateDesc();
		Page<User> userPage = userRepository.findAll(PageRequest.of(1, 5));
		List<User> users = userRepository.findFirst2ByLevel(2, Sort.by("registrationDate"));

		assertAll(() -> assertEquals("Aohnl", user1.getUsername()),
				() -> assertEquals("FirstUser", user2.getUsername()),
				() -> assertEquals(2, users.size()), () -> assertEquals(5, userPage.getSize()),
				() -> assertEquals("Fohny", users.get(0).getUsername()),
				() -> assertEquals("Mohh", users.get(1).getUsername()));
	}

	@Test
	void testFindByLevel() {
		Sort.TypedSort<User> user = Sort.sort(User.class);
		List<User> users = userRepository.findByLevel(3, user.by(User::getRegistrationDate).descending());

		assertAll(() -> assertEquals(3, users.size()), () -> assertEquals("Johne", users.get(0).getUsername()));

	}

}
