package com.ndobriukha.onlinemarketplace.models;

import java.util.Date;

import com.ndobriukha.onlinemarketplace.dao.Identified;

public class Item implements Identified<Integer> {

	private Integer id = null;
	private int sellerId;
	private String description;
	private double startPrice;
	private int timeLeft;
	private Date startBidding;
	private boolean buyItNow;
	private double bidIncrement;

	public Item() {
	}

	@Override
	public Integer getId() {
		return id;
	}

	public int getSeller() {
		return sellerId;
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
