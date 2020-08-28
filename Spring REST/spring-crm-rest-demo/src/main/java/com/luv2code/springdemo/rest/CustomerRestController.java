package com.luv2code.springdemo.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		return customerService.getCustomers();
		
	}
	
	// add mapping for GET /customers/{customerId}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - "+customerId);
		}
		
		return theCustomer;
	}
	
	// Add mapping for POST /customers - add new customer
	// Use @RequestBody to access the request body as a POJO
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// Also just in case to pass an id in JSON .. set id to 0 
		// this is force a save of new item instead of update
		theCustomer.setId(0);
		
		//Delegate call to customer service.
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	// Add mapping for PUT /customers - Update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		// Since customer Id is set, DAO will update the customer in the database
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// Add mapping for DELETE /customers/{customerId} - delete Customer
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		
		//Checks to see if customer is null, then throw exception
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		// throw exception if null
		if(tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - "+customerId);
		}
		customerService.deleteCustomer(customerId);
		
		return "Deleted Customer id: " +customerId;
	}
}



