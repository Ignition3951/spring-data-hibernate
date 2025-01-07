package com.utk.repo;

import java.util.List;

import com.utk.entity.CreditCard;

public interface CrediCardRepository extends BillingDetailsRepository<CreditCard, Long> {

	List<CreditCard> findByExpYear(String expYear);

}
