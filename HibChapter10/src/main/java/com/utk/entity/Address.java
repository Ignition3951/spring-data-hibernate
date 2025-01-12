package com.utk.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

	@NotNull
	@Column(nullable = false)
	private String street;

	@NotNull
	@Column(nullable = false)
	private String city;

	@NotNull
	@Column(nullable = false, length = 5)
	private String zipcode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
