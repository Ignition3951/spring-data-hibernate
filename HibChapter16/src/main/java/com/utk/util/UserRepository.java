package com.utk.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
