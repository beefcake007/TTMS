package com.fanye.model;

public class Seat {

	private int seatId;
	private int scheduleId;
	private int customerId;
	private int seatRow;
	private int seatColumn;
	private int seatIsActive;
	
	
	public Seat() {
		super();
	}
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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
	public int getSeatIsActive() {
		return seatIsActive;
	}
	public void setSeatIsActive(int seatIsActive) {
		this.seatIsActive = seatIsActive;
	}
	
	
}
