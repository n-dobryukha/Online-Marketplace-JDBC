package com.ndobriukha.onlinemarketplace.models;

public class Bid {

	private Integer id = null;
	private Item item;
	private User bidder;
	private double amount;
	
	public Bid() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public Item getItem() {
		return item;
	}

	public User getBidder() {
		return bidder;
	}

	public double getAmount() {
		return amount;
	}
	
	
}
