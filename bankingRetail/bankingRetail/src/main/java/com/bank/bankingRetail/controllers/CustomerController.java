package com.bank.bankingRetail.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankingRetail.entity.Customer;

@RestController
@RequestMapping("/bank")
public class CustomerController {
	private List<Customer> theCustomer;

	// Define @PostConstruct to load customer data only once.
	@PostConstruct
	public void loadData() {
		theCustomer = new ArrayList<>();
		theCustomer.add(new Customer("Sayantan", "123456789", "Kolkata", 2000, false));
		theCustomer.add(new Customer("Sajeed", "987654321", "Haldibari", 2000, true));
		theCustomer.add(new Customer("Sourav", "147852369", "Jalpaiguri", 2000, false));
		theCustomer.add(new Customer("Kaustav", "369852147", "Taliganj", 2000, false));
		theCustomer.add(new Customer("Basak", "753951258", "Jadavpur", 2000, true));
		theCustomer.add(new Customer("Burman", "987159654", "Ranchi", 2000, false));
		theCustomer.add(new Customer("Amlan", "357951456", "Bhubeneshwar", 2000, true));
		theCustomer.add(new Customer("Abhishek", "202505909", "Dumdum", 2000, false));
	}

	// define end point for "/customers" - return list of customers
	@GetMapping("/customers")
	public List<Customer> showCustomers() {
		return theCustomer;
	}

	// define end point for "/customers/{customerId}" - return customer at index
	@GetMapping("/customers/{customerId}")
	public Customer showCustomer(@PathVariable int customerId) {
		// Just index into the list
		// check the customerId against the list size
//      if ((customerId >= theCustomer.size()) || (customerId < 0)) {
//         throw new CustomerNotFoundException("Student Id not found - " + customerId);
//      }
		return theCustomer.get(customerId - 1); // theStudents is the name of the list
	}

	// Creating new customer
	@PostMapping("/newCustomer")
	public String addCustomer(@RequestBody Customer createCustomer) {
		theCustomer.add(createCustomer);
		return "Customer added successfully!!";
	}

	// Update existing customer
	@PutMapping("/updateCustomer/{customerId}")
	public String setCustomer(@RequestBody Customer editCustomer, @PathVariable int customerId) {
		theCustomer.set(customerId - 1, editCustomer);
		return "Customer information updated successfully!!";
	}

	// Delete existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		theCustomer.remove(customerId - 1);
		return "Customer Deleted Successfully";
	}
}
