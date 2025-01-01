package com.utk;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

public class QueryResultTests extends SpringDataJpaApplicationTest {

	@Test
	void testFindByAsArraysAndSort() {

		List<Object[]> userList1 = userRepository.findByAsArrayAndSort("oh", Sort.by("username"));
		// List<Object[]> userList2 = userRepository.findByAsArrayAndSort("oh",
		// Sort.by("LENGTH(u.email)").descending());
		List<Object[]> userList3 = userRepository.findByAsArrayAndSort("oh", JpaSort.unsafe("LENGTH(u.email)"));

		assertAll(() -> assertEquals(9, userList1.size()), () -> assertEquals("Aohnl", userList1.get(0)[0]),
				() -> assertEquals(0, userList1.get(0)[1]), // u.username, LENGTH(u.email) array giving result in
															// username and length of email
//				() -> assertEquals(9, userList2.size()),
//				() -> assertEquals("Oohnr", userList2.get(0)[0]), () -> assertEquals(26, userList2.get(0)[1]),
				() -> assertEquals(9, userList3.size()), () -> assertEquals("Fohny", userList3.get(0)[0]),
				() -> assertEquals(0, userList3.get(0)[1]));
	}

}
