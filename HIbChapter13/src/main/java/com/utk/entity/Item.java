package com.utk.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.utk.interceptor.Auditable;

@Entity
//@EntityListeners(PersistEntityListener.class)
//@ExcludeDefaultListeners
@Audited
@org.hibernate.annotations.Filter(name = "limitByUserRanking", condition = """
		:currentUserRanking >= (
		        select u.RANKING from USERS u
		        where u.ID = SELLER_ID
		    )""")
public class Item implements Auditable {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	private String name;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@NotAudited
	private Category category;

	@OneToMany(mappedBy = "item", cascade = { javax.persistence.CascadeType.DETACH,
			javax.persistence.CascadeType.MERGE })
	@NotAudited
	private Set<Bid> bids = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELLER_ID", nullable = false)
	@Cascade(CascadeType.REPLICATE)
	private User seller;

	public Item() {
	}

	public Item(@NotNull String name, User seller) {
		this.name = name;
		this.seller = seller;
	}

	public Item(@NotNull String name) {
		this.name = name;
	}

	public Item(@NotNull String name, @NotNull Category category, User seller) {
		this.name = name;
		this.category = category;
		this.seller = seller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Bid> getBids() {
		return Collections.unmodifiableSet(bids);
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public void addBid(Bid bid) {
		bids.add(bid);
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Long getId() {
		return id;
	}

}
