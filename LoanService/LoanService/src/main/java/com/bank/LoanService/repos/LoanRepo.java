package com.bank.LoanService.repos;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.LoanService.entity.Customer;

@FeignClient("BANKINGRETAIL")
@RequestMapping("/bank")
public interface LoanRepo {

	@GetMapping("/customers")
	public List<Customer> showCustomers();
	
	@GetMapping("/customers/{customerId}")
	public Customer showCustomerById(@PathVariable int customerId);
	
//	@PutMapping("/loanUpdateCustomer")
//	public String setLoanCustomer(Customer editCustomer, int customerId);
	
}
