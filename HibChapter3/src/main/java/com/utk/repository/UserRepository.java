package com.utk.repository;

import org.springframework.data.repository.CrudRepository;

import com.utk.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
