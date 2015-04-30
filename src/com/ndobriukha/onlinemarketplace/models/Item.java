package com.ndobriukha.onlinemarketplace.models;

import java.util.Date;

public class Item {

	private Integer id = null;
	private User seller;
	private String description;
	private double startPrice;
	private int timeLeft;
	private Date startBidding;
	private boolean buyItNow;
	private double bidIncrement;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public User getSeller() {
		return seller;
	}

	public String getDescription() {
		return description;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public Date getStartBidding() {
		return startBidding;
	}

	public boolean isBuyItNow() {
		return buyItNow;
	}

	public double getBidIncrement() {
		return bidIncrement;
	}
	
	
}
