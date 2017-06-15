package com.fanye.model;

import java.util.Date;

public class Order {

	private int orderId;
	private int seatId;
	private int customerId;
	private String customerName;
	private int scheduleId;
	private float orderAdjustedPrice;
	private Date orderBuyDate;
	private String movieName;
	private String className;
	private String hallName;
	private int seatRow;
	private int seatColumn;
	
	
	public Order() {
		super();
	}
	
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public float getOrderAdjustedPrice() {
		return orderAdjustedPrice;
	}
	public void setOrderAdjustedPrice(float orderAdjustedPrice) {
		this.orderAdjustedPrice = orderAdjustedPrice;
	}
	public Date getOrderBuyDate() {
		return orderBuyDate;
	}
	public void setOrderBuyDate(Date orderBuyDate) {
		this.orderBuyDate = orderBuyDate;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	
}
