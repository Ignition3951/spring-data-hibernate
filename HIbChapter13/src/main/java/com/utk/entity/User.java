package com.utk.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	private String username;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "USER_ID", nullable = false)
	private Set<BillingDetails> billingDetails = new HashSet<>();

	public User() {
	}

	public User(@NotNull String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<BillingDetails> getBillingDetails() {
		return billingDetails;
	}

//	public void setBillingDetails(Set<BillingDetails> billingDetails) {
//		this.billingDetails = billingDetails;
//	}

	public void addBillingDetails(BillingDetails billingDetails) {
		this.billingDetails.add(billingDetails);
	}

	public Long getId() {
		return id;
	}

}
