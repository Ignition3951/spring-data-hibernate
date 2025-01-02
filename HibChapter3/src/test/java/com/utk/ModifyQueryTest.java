package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import com.utk.entity.User;

public class ModifyQueryTest extends SpringDataJpaApplicationTest {

	@Test
	void testmodifyLevel() {
		int updateLevel = userRepository.updateLevel(5, 15);
		List<User> users = userRepository.findByLevel(15, Sort.by("username"));

		assertAll(() -> assertEquals(5, users.size()), () -> assertEquals(5, updateLevel),
				() -> assertEquals("Aohnt", users.get(0).getUsername()));

	}

	@Test
	void testDeleteByLevel() {
		int deletedRows = userRepository.deleteByLevel(3);
		List<User> users = userRepository.findByLevel(3, Sort.by("username"));

		assertAll(() -> assertEquals(0, users.size()), () -> assertEquals(3, deletedRows));
	}

	@Test()
	void testDeleteBulkByLevel() {
		int deletedRows = userRepository.deleteBulkByLevel(2);
		List<User> users = userRepository.findByLevel(2, Sort.by("username"));

		assertAll(() -> assertEquals(0, users.size()), () -> assertEquals(2, deletedRows));
	}

}
