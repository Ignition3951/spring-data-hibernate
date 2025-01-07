package com.utk.repo;

import java.util.List;

import com.utk.entity.BankAccount;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {

	List<BankAccount> findBySwift(String swift);

}
