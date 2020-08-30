package com.sayantan.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sayantan.springcloud.model.Product;
import com.sayantan.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	ProductRepo repo;
	
	@RequestMapping(value="/products",method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		
		return repo.save(product);
	}
	
}
