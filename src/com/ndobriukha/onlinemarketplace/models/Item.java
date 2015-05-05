package com.ndobriukha.onlinemarketplace.models;

import java.util.Date;

import com.ndobriukha.onlinemarketplace.dao.Identified;

public class Item implements Base, Identified<Integer> {

	public static enum BuyItNowType {
		Y, N;
	}
	
	private Integer id = null;
	private Integer sellerId;
	private String description;
	private double startPrice;
	private int timeLeft;
	private Date startBidding;
	private BuyItNowType buyItNow;
	private double bidIncrement;

	public Item() {
	}

	public Item(int sellerId, String description, double startPrice,
			int timeLeft, Date startBidding, BuyItNowType buyItNow,
			double bidIncrement) {
		this.sellerId = sellerId;
		this.description = description;
		this.startPrice = startPrice;
		this.timeLeft = timeLeft;
		this.startBidding = startBidding;
		this.buyItNow = buyItNow;
		this.bidIncrement = bidIncrement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public Date getStartBidding() {
		return startBidding;
	}

	public void setStartBidding(Date startBidding) {
		this.startBidding = startBidding;
	}

	public boolean isBuyItNow() {
		return (buyItNow == BuyItNowType.Y);
	}
	
	public BuyItNowType getBuyItNow() {
		return buyItNow;
	}

	public void setBuyItNow(String buyItNow) {
		this.buyItNow = BuyItNowType.valueOf(buyItNow);
	}

	public double getBidIncrement() {
		return bidIncrement;
	}

	public void setBidIncrement(double bidIncrement) {
		this.bidIncrement = bidIncrement;
	}

	@Override
	public Object[] getFieldsValues() {
		/*return new Object[] { getSellerId(), getDescription(), getStartPrice(),
				getTimeLeft(), getStartBidding(), isBuyItNow(),
				getBidIncrement() };*/
		return new Object[] { getSellerId(), getDescription(), getStartPrice(), getTimeLeft(), "Y" };
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bidIncrement);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((buyItNow == BuyItNowType.Y) ? 1231 : 1237);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + sellerId;
		result = prime * result
				+ ((startBidding == null) ? 0 : startBidding.hashCode());
		temp = Double.doubleToLongBits(startPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + timeLeft;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (Double.doubleToLongBits(bidIncrement) != Double
				.doubleToLongBits(other.bidIncrement))
			return false;
		if (buyItNow != other.buyItNow)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sellerId != other.sellerId)
			return false;
		if (startBidding == null) {
			if (other.startBidding != null)
				return false;
		} else if (!startBidding.equals(other.startBidding))
			return false;
		if (Double.doubleToLongBits(startPrice) != Double
				.doubleToLongBits(other.startPrice))
			return false;
		if (timeLeft != other.timeLeft)
			return false;
		return true;
	}
}
