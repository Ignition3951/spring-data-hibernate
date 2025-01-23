package com.utk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "USERS")
//@ExcludeDefaultListeners
@Audited
public class User {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	private String username;

//	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "USER_ID", nullable = false)
//	private Set<BillingDetails> billingDetails = new HashSet<>();

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

//	public Set<BillingDetails> getBillingDetails() {
//		return billingDetails;
//	}
//
//	public void setBillingDetails(Set<BillingDetails> billingDetails) {
//		this.billingDetails = billingDetails;
//	}
//
//	public void addBillingDetails(BillingDetails billingDetails) {
//		this.billingDetails.add(billingDetails);
//	}

	public Long getId() {
		return id;
	}

//	@PostPersist
//	public void logMessage() {
//		User currentUser = CurrentUser.INSTANCE.get();
//		Log log = Log.INSTANCE;
//		log.save("Entity instance persisted by " + currentUser.getUsername() + ": " + this);
//	}

}
