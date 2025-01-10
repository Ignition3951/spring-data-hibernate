package com.utk.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Bid;
import com.utk.entity.Item;

public interface BidRepository extends JpaRepository<Bid, Long> {

	Set<Bid> findByItem(Item item);

}
