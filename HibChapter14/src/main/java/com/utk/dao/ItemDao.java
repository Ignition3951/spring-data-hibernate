package com.utk.dao;

import java.util.List;

import com.utk.entity.Item;

public interface ItemDao {

	Item getbyId(long id);

	List<Item> findAll();

	void insert(Item item);

	void update(long id, String name);

	void delete(Item item);

	Item findByName(String name);

}
