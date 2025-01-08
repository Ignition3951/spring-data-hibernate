package com.utk.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@ElementCollection
	@CollectionTable(name = "IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
	@Column(name = "FILENAME")
	private Set<String> images = new HashSet<>();

	public Item() {
		super();
	}

	public Item(Set<String> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public Set<String> getImages() {
		return Collections.unmodifiableSet(images);
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public void add(String imageName) {
		images.add(imageName);
	}

//	@Override
//	public String toString() {
//		return "Item [id=" + id + ", images=" + images + "]";
//	}

}
