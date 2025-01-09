package com.utk.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@ElementCollection
	@CollectionTable(name = "IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
	@Column(name = "FILENAME")
	@GenericGenerator(name = "sequence_gen", strategy = "sequence")
	@org.hibernate.annotations.CollectionId( // Surrogate PK allows duplicates!
			columns = @Column(name = "IMAGE_ID"), type = @org.hibernate.annotations.Type(type = "long"), generator = "sequence_gen")
	private Collection<String> images = new ArrayList<>();

	public Item() {
	}

	public Item(Set<String> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public Collection<String> getImages() {
		return Collections.unmodifiableCollection(images);
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
