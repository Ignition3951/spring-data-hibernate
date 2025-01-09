package com.utk.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	private String name;

	@ElementCollection
	@CollectionTable(name = "IMAGES")
	@MapKeyColumn(name = "FILENAME")
	@Column(name = "IMAGENAME")
	private Map<String, String> images = new HashMap<>();

	public Item() {
	}

	public Item(Map<String, String> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public Map<String, String> getImages() {
		return Collections.unmodifiableMap(images);
	}

	public void setImages(Map<String, String> images) {
		this.images = images;
	}

	public void add(String key, String value) {
		images.put(key, value);
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
