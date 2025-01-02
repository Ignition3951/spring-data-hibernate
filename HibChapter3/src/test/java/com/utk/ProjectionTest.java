package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.utk.entity.User;
import com.utk.projection.Projection;

public class ProjectionTest extends SpringDataJpaApplicationTest {

	@Test
	void testProjectionUsername() {

		List<Projection.UsernameOnly> users = userRepository.findByEmail("sadasdsomeEmailasad.com");

		assertAll(() -> assertEquals(1, users.size()), () -> assertEquals("Aohnt", users.get(0).getUsername()));
	}

	@Test
	void testProjectionUserSummary() {
		List<Projection.UserSummary> users = userRepository
				.findByRegistrationDateAfter(LocalDate.of(2024, Month.JULY, 1));

		assertAll(() -> assertEquals(7, users.size()), () -> assertEquals("FirstUser", users.get(0).getUsername()),
				() -> assertEquals(null, users.get(0).getInfo()));

	}

	@Test
	void testDynamicProjection() {
		List<Projection.UsernameOnly> usernames = userRepository.findByEmail("sadasdsomeEmailasad.com",
				Projection.UsernameOnly.class);
		List<User> users = userRepository.findByEmail("sadasdsomeEmailasad.com", User.class);

		assertAll(() -> assertEquals(1, usernames.size()), () -> assertEquals("Aohnt", usernames.get(0).getUsername()),
				() -> assertEquals(1, users.size()), () -> assertEquals("Aohnt", users.get(0).getUsername()));

	}

}
