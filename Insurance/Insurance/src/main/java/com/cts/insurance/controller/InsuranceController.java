package com.cts.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.insurance.dto.Insurance;
import com.cts.insurance.exception.MissingQueryParam;
import com.cts.insurance.service.Insurance_Service;

@RestController
@RequestMapping("/insurance/service")
public class InsuranceController {
	
	@Autowired
	Insurance_Service service;
	
	
	// Get All Insurances 
	// For convenience
	
	@GetMapping("/insurances")
	public List<Insurance> showInsurance() {
		return this.service.getAllInsurances();
	}
	
	// Create New Insurance	
	
	@PostMapping("/add")
	public Insurance addInsurance(@RequestBody Insurance insurance) {
		this.service.addInsurances(insurance);
		return null;
	}
	
	
	// Update Existing Insurance
	
	@PutMapping("/update")
	public Insurance editInsurance(@RequestBody Insurance insurance) {
		this.service.updateInsurances(insurance);
		return null;
	}
	
	
// Using Model Attribute	
	
	@GetMapping("/get")
	public Insurance getInsurance(@ModelAttribute("insurance_id") String iId,@ModelAttribute("customer_id") String cId) {
		
		if ((iId.length() != 0)&&(cId.length() != 0)) {
			return this.service.findInsuranceByIIdandCId(iId,cId);
		}

		else if (cId.length() != 0)
			return this.service.findInsuranceByCId(cId);
		
		else if (iId.length() != 0)
			return this.service.findInsuranceByIId(iId);
		
		else
			return this.service.throwMissing(iId, cId);
	}

}
