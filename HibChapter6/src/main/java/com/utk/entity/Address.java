package com.utk.entity;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

	@NotNull
	@Column(nullable = false)
	private String street;

	@NotNull
	@Column(nullable = false, length = 5)
	private String zipcode;

	@NotNull
	@Column(nullable = false)
	private String city;

	/*
	 * Hibernate will call this no-argument constructor to create an instance, and
	 * then populate the fields directly.
	 */
	public Address() {
	}

	/**
	 * You can have additional (public) constructors for convenience.
	 */
	public Address(String street, String zipcode, String city) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
