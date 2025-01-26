package com.utk.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.utk.dao.ItemDao;
import com.utk.entity.Bid;
import com.utk.entity.Item;

@Repository
@Transactional
public class ItemDaoImpl implements ItemDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public Item getbyId(long id) {
		return entityManager.find(Item.class, id);
	}

	@Override
	public List<Item> findAll() {
		return entityManager.createQuery("from Item", Item.class).getResultList();
	}

	@Override
	public void insert(Item item) {
		entityManager.persist(item);
		for (Bid bid : item.getBids()) {
			entityManager.persist(bid);
		}
	}

	@Override
	public void update(long id, String name) {
		Item item = entityManager.find(Item.class, id);
		item.setName(name);
		entityManager.persist(item);
	}

	@Override
	public void delete(Item item) {
		for (Bid bid : item.getBids()) {
			entityManager.remove(bid);
		}
		entityManager.remove(item);
	}

	@Override
	public Item findByName(String name) {
		Item item = entityManager.createQuery("from Item where name=:name", Item.class).setParameter("name", name)
				.getSingleResult();
		return item;
	}

}
