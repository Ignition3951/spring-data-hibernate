package com.utk.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.utk.entity.Bid;

@Repository
@Transactional
public class BidDaoImpl extends AbstractGenericDao<Bid> {

	public BidDaoImpl() {
		setClass(Bid.class);
	}

}
