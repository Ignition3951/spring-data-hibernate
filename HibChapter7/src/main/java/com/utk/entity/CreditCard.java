package com.utk.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
//@AttributeOverride(name = "owner", column = @Column(name = "CC_OWNER", nullable = false))
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {

	@NotNull
	private String cardNumber;

	@NotNull
	private String expMonth;

	@NotNull
	private String expYear;

	public CreditCard() {
	}

	public CreditCard(@NotNull String owner, @NotNull String cardNumber, @NotNull String expMonth,
			@NotNull String expYear) {
		setOwner(owner);
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", expMonth=" + expMonth + ", expYear=" + expYear
				+ ", getCardNumber()=" + getCardNumber() + ", getExpMonth()=" + getExpMonth() + ", getExpYear()="
				+ getExpYear() + ", getOwner()=" + getOwner() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
