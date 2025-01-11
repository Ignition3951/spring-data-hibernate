package com.utk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utk.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
