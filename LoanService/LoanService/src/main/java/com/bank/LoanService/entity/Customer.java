package com.bank.LoanService.entity;

public class Customer {

//	private int id;
	private String name;
	private String phone;
	private String address;
	private int balance;
	private boolean loan;
	
	public Customer( String name, String phone, String address, int balance, boolean loan) {
		super();
//		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
		this.loan = loan;
	}

//	public int getId() {
//		return id;
//	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isLoan() {
		return loan;
	}

	public void setLoan(boolean loan) {
		this.loan = loan;
	}
}