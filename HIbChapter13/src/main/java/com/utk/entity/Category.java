package com.utk.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;

@Entity
public class Category {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	private String name;

	@OneToMany(mappedBy = "category")
	@Filter(name = "limitByUserRanking", condition = """
			:currentUserRanking >= (
			        select u.RANKING from USERS u
			        where u.ID = SELLER_ID
			        )""")
	private Set<Item> items = new HashSet<>();

	public Category() {
	}

	public Category(@NotNull String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public Set<Item> getItems() {
		return Collections.unmodifiableSet(items);
	}

}
