package com.utk.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String name;

	@ElementCollection
	@CollectionTable(name = "IMAGES")
	@OrderColumn(name = "IMAGE_ID")
	@Column(name = "FILENAME")
	private List<String> images = new ArrayList<>();

	public Item() {
	}

	public Item(List<String> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public Collection<String> getImages() {
		return Collections.unmodifiableCollection(images);
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void add(String imageName) {
		images.add(imageName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public String toString() {
//		return "Item [id=" + id + ", images=" + images + "]";
//	}

}
