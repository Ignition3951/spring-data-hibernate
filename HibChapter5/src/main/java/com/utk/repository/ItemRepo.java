package com.utk.repository;

import org.springframework.data.repository.CrudRepository;

import com.utk.entity.Item;

public interface ItemRepo extends CrudRepository<Item, Long> {

}
