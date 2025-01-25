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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

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

	@NotNull
	private int ranking = 0;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "USER_ID", nullable = false)
	@NotAudited
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

	public void setBillingDetails(Set<BillingDetails> billingDetails) {
		this.billingDetails = billingDetails;
	}

	public void addBillingDetails(BillingDetails billingDetails) {
		this.billingDetails.add(billingDetails);
	}

	public User(@NotNull String username, @NotNull int ranking) {
		this.username = username;
		this.ranking = ranking;
	}

	public Long getId() {
		return id;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

//	@PostPersist
//	public void logMessage() {
//		User currentUser = CurrentUser.INSTANCE.get();
//		Log log = Log.INSTANCE;
//		log.save("Entity instance persisted by " + currentUser.getUsername() + ": " + this);
//	}


}
