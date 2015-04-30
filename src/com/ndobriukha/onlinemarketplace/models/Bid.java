package com.ndobriukha.onlinemarketplace.models;

import com.ndobriukha.onlinemarketplace.dao.Identified;

public class Bid implements Identified<Integer> {

	private Integer id = null;
	private int bidderId;
	private int itemId;	
	private double amount;
	
	public Bid() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer getId() {
		return id;
	}

	public int getItemId() {
		return itemId;
	}

	public int getBidderId() {
		return bidderId;
	}

	public double getAmount() {
		return amount;
	}
	
	
}
