package com.utk.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import com.utk.entity.User;
import com.utk.projection.Projection;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	List<User> findAllByOrderByUsernameAsc();

	List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);

	List<User> findByUsernameAndEmail(String username, String email);

	List<User> findByUsernameOrEmail(String username, String email);

	List<User> findByUsernameIgnoreCase(String username);

	List<User> findByLevelOrderByUsernameDesc(int level);

	List<User> findByLevelGreaterThanEqual(int level);

	List<User> findByUsernameContaining(String text);

	List<User> findByUsernameLike(String text);

	List<User> findByUsernameStartingWith(String start);

	List<User> findByUsernameEndingWith(String end);

	List<User> findByIsActive(boolean isActive);

	List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

	List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

	User findFirstByOrderByUsernameAsc();

	User findTopByOrderByRegistrationDateDesc();

	Page<User> findAll(Pageable pageable);

	List<User> findFirst2ByLevel(int level, Sort sort);

	List<User> findByLevel(int level, Sort sort);

	List<User> findByIsActive(boolean isActive, Pageable pageable);

	Streamable<User> findByEmailContaining(String text);

	Streamable<User> findByLevel(int level);

	@Query("select count(u) from User u where u.isActive = ?1")
	int findNumberOfUsersbyActivity(boolean isActive);

	@Query("select u from User u where u.level = :level and u.isActive = :isActive")
	List<User> findByLevelAndActive(@Param("level") int level, @Param("active") boolean isActive);

	@Query(value = "select count(*) from users where is_active = ?1", nativeQuery = true)
	int findNumberOfUsersByActivityNative(boolean isActive);

	@Query("select u.username, LENGTH(u.email) from #{#entityName} u where u.username like %?1%")
	List<Object[]> findByAsArrayAndSort(String text, Sort sort);

	List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);

	List<Projection.UsernameOnly> findByEmail(String username);

	<T> List<T> findByEmail(String username, Class<T> type);

}
