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
	@GetMapping("/insurances")
	public List<Insurance> showInsurance() {
		return this.service.getAllInsurances();
	}
	
	// Create New Insurance	
	@PostMapping("/add")
	public void addInsurance(@RequestBody Insurance insurance) {
		this.service.addInsurances(insurance);
	}
	
	
	// Update Existing Insurance
	@PutMapping("/update")
	public void editInsurance(@RequestBody Insurance insurance) {
		this.service.updateInsurances(insurance);
	}
	
// Using Request Params
	
//	@GetMapping("/get")
//	public Insurance getInsurance(@RequestParam String iId,@RequestParam String cId) {
//		if (iId.equalsIgnoreCase(""))
//			return this.service.findInsuranceByCId(cId);
//		else if (cId.equalsIgnoreCase(""))
//			return this.service.findInsuranceByIId(iId);
//		else 
//		    return this.service.findInsuranceByIIdandCId(iId,cId);
//	}
	
// Using Model Attribute	
	
	@GetMapping("/get")
	public Insurance getInsurance(@ModelAttribute("insurance_id") String iId,@ModelAttribute("customer_id") String cId) {
		
		if ((iId.equalsIgnoreCase(""))&&(cId.equalsIgnoreCase(""))) {
//			return this.service.noQueryParams();
			throw new MissingQueryParam();
		}

		else if (iId.equalsIgnoreCase(""))
			return this.service.findInsuranceByCId(cId);
		
		else if (cId.equalsIgnoreCase(""))
			return this.service.findInsuranceByIId(iId);
		
		else
			return this.service.findInsuranceByIIdandCId(iId,cId);
	}
	
	
	
	
	
//	@GetMapping("/get")
//	public String getInsurance(@RequestParam String id) {
//		return id;
//	}
//	

}
