package com.ndobriukha.onlinemarketplace.models;

import com.ndobriukha.onlinemarketplace.dao.Identified;

public class User implements Identified<Integer> {
	public void setId(Integer id) {
		this.id = id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private Integer id = null;
	private String fullName;
	private String billingAddress;
	private String login;
	private String password;
	private String email;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}
	
	public User(String fullName, String billingAddress, String login,
			String password, String email) {
		this.fullName = fullName;
		this.billingAddress = billingAddress;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	@Override
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
