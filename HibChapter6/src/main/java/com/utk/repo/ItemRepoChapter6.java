package com.utk.repo;

import org.springframework.data.repository.CrudRepository;

import com.utk.entity.ItemChapter6;

public interface ItemRepoChapter6 extends CrudRepository<ItemChapter6, Long> {

	Iterable<ItemChapter6> findByMetricWeight(double weight);
}
