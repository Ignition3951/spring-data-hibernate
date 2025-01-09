package com.utk.entity;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.SortComparator;

import com.utk.util.ReverseStringComparator;

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
	@SortComparator(ReverseStringComparator.class)
	private SortedMap<String, String> images = new TreeMap<>();

	public Item() {
	}

	public Item(SortedMap<String, String> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public SortedMap<String, String> getImages() {
		return Collections.unmodifiableSortedMap(images);
	}

	public void setImages(SortedMap<String, String> images) {
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
