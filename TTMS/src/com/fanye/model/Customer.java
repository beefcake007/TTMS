package com.fanye.model;

public class Customer {

	private int customerId;
	private String customerName;
	private String customerPassWord;
	private String customerEmail;
	private String customerMobile;
	private int classId=-1;
	private String className;
	private int seatId;


	public Customer() {
		super();
	}
	
	
	public Customer(String customerName, String customerPassWord,
			String customerEmail, String customerMobile) {
		super();
		this.customerName = customerName;
		this.customerPassWord = customerPassWord;
		this.customerEmail = customerEmail;
		this.customerMobile = customerMobile;
	}


	public Customer(String customerName, String customerPassWord,
			String customerEmail, String customerMobile, int classId) {
		super();
		this.customerName = customerName;
		this.customerPassWord = customerPassWord;
		this.customerEmail = customerEmail;
		this.customerMobile = customerMobile;
		this.classId = classId;
	}


	public Customer(String customerName, String customerPassWord) {
		super();
		this.customerName = customerName;
		this.customerPassWord = customerPassWord;
	}


	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerPassWord() {
		return customerPassWord;
	}

	public void setCustomerPassWord(String customerPassWord) {
		this.customerPassWord = customerPassWord;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	
}
