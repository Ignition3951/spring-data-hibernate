package com.utk.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.utk.entity.Bid;
import com.utk.entity.Item;

@Repository
@Transactional
public class ItemDaoImpl extends AbstractGenericDao<Item> {

	public ItemDaoImpl() {
		setClass(Item.class);
	}

	@Override
	public void insert(Item entity) {
		entityManager.persist(entity);
		for (Bid bid : entity.getBids()) {
			entityManager.persist(bid);
		}
	}

	@Override
	public void delete(Item entity) {
		for (Bid bid : entity.getBids()) {
			entityManager.remove(bid);
		}
		entityManager.remove(entity);
	}
}
