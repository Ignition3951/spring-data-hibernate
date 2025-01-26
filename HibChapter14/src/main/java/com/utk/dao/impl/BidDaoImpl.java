package com.utk.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.utk.dao.BidDao;
import com.utk.entity.Bid;

@Repository
@Transactional
public class BidDaoImpl implements BidDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public Bid getById(long id) {
		return entityManager.find(Bid.class, id);
	}

	@Override
	public List<Bid> getAll() {
		return entityManager.createQuery("from Bid", Bid.class).getResultList();
	}

	@Override
	public void insert(Bid bid) {
		entityManager.persist(bid);
	}

	@Override
	public void update(long id, String amount) {
		Bid bid = entityManager.find(Bid.class, id);
		bid.setAmount(new BigDecimal(amount));
		entityManager.persist(bid);
	}

	@Override
	public void delete(Bid bid) {
		entityManager.remove(bid);
	}

	@Override
	public List<Bid> findByAmount(String amount) {
		List<Bid> bids = entityManager.createQuery("from Bid where amount=:amount", Bid.class)
				.setParameter("amount", new BigDecimal(amount)).getResultList();
		return bids;
	}


}
