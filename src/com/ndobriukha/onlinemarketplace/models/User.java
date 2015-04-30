package com.ndobriukha.onlinemarketplace.models;

import com.ndobriukha.onlinemarketplace.dao.Identified;

public class User implements Identified<Integer> {
	private Integer id = null;
	private String fullName;
	private String billingAddress;
	private String login;
	private String password;
	private String email;
	
	public User(String fullName, String billingAddress, String login, String password, String email) {
		this.fullName = fullName;
		this.billingAddress = billingAddress;
		this.login = login;
		this.password = password;
		this.email = email;
	}
	
	public User(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	
}
