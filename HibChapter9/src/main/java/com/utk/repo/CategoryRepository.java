package com.utk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
