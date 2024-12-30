package com.utk.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.utk.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
