package com.utk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
