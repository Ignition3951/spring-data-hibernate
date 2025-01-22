package com.utk.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class CreditCard extends BillingDetails {

	@NotNull
	private String cardNumber;

	@NotNull
	private String expYear;

	@NotNull
	private String expMonth;

	public CreditCard() {
	}

	public CreditCard(String owner, @NotNull String cardNumber, @NotNull String expYear, @NotNull String expMonth) {
		super(owner);
		this.cardNumber = cardNumber;
		this.expYear = expYear;
		this.expMonth = expMonth;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

}
