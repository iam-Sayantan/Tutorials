package com.bank.LoanService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.LoanService.entity.Customer;
import com.bank.LoanService.repos.LoanRepo;

@RestController
//@RequestMapping("/loan")
public class LoanController {
	
	@Autowired
	private LoanRepo repo;
	
	@GetMapping("/loan/{loanId}")
	public String checkLoan(@PathVariable int loanId) {
		Customer cust = repo.showCustomerById(loanId);
		if(cust.isLoan()) {
			return "Loan not sanctioned";
		} else {
			return "Loan sanctioned";
		}
	}
}
