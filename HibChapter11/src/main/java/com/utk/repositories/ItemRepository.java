package com.utk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

	Optional<Item> findByName(String name);
}
