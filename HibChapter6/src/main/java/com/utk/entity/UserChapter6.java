package com.utk.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class UserChapter6 extends User {

	public UserChapter6() {
	}

	private Address homeAddress;

	@AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET"))
	@AttributeOverride(name = "zipcode", column = @Column(name = "BILLING_ZIPCODE", length = 5))
	@AttributeOverride(name = "city", column = @Column(name = "BILLING_CITY"))
	private Address billingAddress;

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

}
