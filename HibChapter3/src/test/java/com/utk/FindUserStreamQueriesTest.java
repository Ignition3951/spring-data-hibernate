package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.utk.entity.User;

public class FindUserStreamQueriesTest extends SpringDataJpaApplicationTest {

	@Test
	void testStreamable() {
		try (Stream<User> result = userRepository.findByEmailContaining("someEmail").and(userRepository.findByLevel(5))
				.stream().distinct()) {
			assertEquals(10, result.count());
		}
	}
}
