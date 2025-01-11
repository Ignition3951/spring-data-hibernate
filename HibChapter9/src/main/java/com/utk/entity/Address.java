package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	private String street;

	@NotNull
	private String zipcode;

	@NotNull
	private String city;

	public Address() {
	}

	public Address(@NotNull String street, @NotNull String zipcode, @NotNull String city) {
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

	public Long getId() {
		return id;
	}

}
