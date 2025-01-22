package com.utk.entity;

public class CurrentUser extends ThreadLocal<User> {

	public static CurrentUser INSTANCE = new CurrentUser();

	private CurrentUser() {

	}
}
