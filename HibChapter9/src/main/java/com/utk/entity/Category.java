package com.utk.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "category")
	private Set<CategorizedItem> categorizedItems = new HashSet<>();

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCategorizedItems(CategorizedItem categorizedItem) {
		categorizedItems.add(categorizedItem);
	}

}
