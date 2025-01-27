package com.utk.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.utk.dao.GenericDao;
import com.utk.entity.Bid;
import com.utk.entity.Item;

public class DatabaseService {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Autowired
	private GenericDao<Item> itemDao;

	@Transactional
	public void init() {
		for (int i = 0; i < 10; i++) {
			String itemName = "Item " + (i + 1);
			Item item = new Item();
			item.setName(itemName);
			Bid bid1 = new Bid(new BigDecimal(1000), item);
			Bid bid2 = new Bid(new BigDecimal(2000), item);
			itemDao.insert(item);
		}
	}

	@Transactional
	public void clear() {
		entityManager.createQuery("delete from Bid b").executeUpdate();
		entityManager.createQuery("delete from Item i").executeUpdate();
	}

}
