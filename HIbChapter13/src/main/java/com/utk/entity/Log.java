package com.utk.entity;

import java.util.ArrayList;

public class Log extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Log INSTANCE = new Log();

	private Log() {

	}

	public void save(String message) {
		add(message);
	}
}
