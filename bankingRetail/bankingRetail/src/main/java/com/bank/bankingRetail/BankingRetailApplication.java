package com.bank.bankingRetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankingRetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingRetailApplication.class, args);
	}

}