package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Address {

	@Id
	@GeneratedValue(generator = "addressKeyGenerator")
	@GenericGenerator(name = "addressKeyGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	private Long id;

	@NotNull
	private String street;

	@NotNull
	private String zipcode;

	@NotNull
	private String city;

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public Address() {
	}

	public Address(@NotNull String street, @NotNull String zipcode, @NotNull String city) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	public Address(@NotNull String street, @NotNull String zipcode, @NotNull String city, User user) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
		this.user = user;
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
