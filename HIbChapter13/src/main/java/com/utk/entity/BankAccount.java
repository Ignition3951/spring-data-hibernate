package com.utk.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount extends BillingDetails {

	@NotNull
	private String account;

	@NotNull
	private String bankname;

	@NotNull
	private String swift;

	public BankAccount() {
	}

	public BankAccount(String owner, @NotNull String account, @NotNull String bankname, @NotNull String swift) {
		super(owner);
		this.account = account;
		this.bankname = bankname;
		this.swift = swift;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

}
