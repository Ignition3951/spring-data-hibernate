package com.utk.dao;

import java.util.List;

import com.utk.entity.Bid;

public interface BidDao {

	Bid getById(long id);

	List<Bid> getAll();

	void insert(Bid bid);

	void update(long id, String amount);

	void delete(Bid bid);

	List<Bid> findByAmount(String amount);
}
