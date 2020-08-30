package com.sayantan.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sayantan.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
