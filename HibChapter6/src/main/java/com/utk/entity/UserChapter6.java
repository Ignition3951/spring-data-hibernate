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

	@Override
	public String toString() {
		return "UserChapter6 [homeAddress=" + homeAddress + ", billingAddress=" + billingAddress + ", getHomeAddress()="
				+ getHomeAddress() + ", getBillingAddress()=" + getBillingAddress() + ", getUsername()=" + getUsername()
				+ ", getRegistrationDate()=" + getRegistrationDate() + ", getId()=" + getId() + ", getEmail()="
				+ getEmail() + ", getLevel()=" + getLevel() + ", isActive()=" + isActive() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
