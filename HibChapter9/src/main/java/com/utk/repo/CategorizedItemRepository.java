package com.utk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.CategorizedItem;

public interface CategorizedItemRepository extends JpaRepository<CategorizedItem, Long> {

}
