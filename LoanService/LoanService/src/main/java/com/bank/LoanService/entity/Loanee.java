package com.bank.LoanService.entity;

public class Loanee {

	private String name;
	private boolean loan;
	public Loanee(String name, boolean loan) {
		super();
		this.name = name;
		this.loan = loan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isLoan() {
		return loan;
	}
	public void setLoan(boolean loan) {
		this.loan = loan;
	}
}
