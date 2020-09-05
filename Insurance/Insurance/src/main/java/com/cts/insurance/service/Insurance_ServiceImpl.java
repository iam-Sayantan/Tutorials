package com.cts.insurance.service;

import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.cts.insurance.dto.Insurance;
import com.cts.insurance.exception.DifferentQueryParam;
import com.cts.insurance.exception.DuplicateEntry;
import com.cts.insurance.exception.MissingQueryParam;
import com.cts.insurance.exception.NoInsuranceForCustomerId;

@Service
public class Insurance_ServiceImpl implements Insurance_Service {

	List<Insurance> list;
//	static Logger log = Logger.getLogger(Insurance_ServiceImpl.class.getName());

	
	public Insurance_ServiceImpl() {

		list = new ArrayList<>();
//		list.add(new Insurance("234234234", "INSNAME1", "medical"));
//		list.add(new Insurance( "234234235", "INSNAME2", "car"));
//		list.add(new Insurance( "234234236", "INSNAME3", "house"));
	}
	
	// Get all insurances

	@Override
	public List<Insurance> getAllInsurances() {
		return list;
	}
	
	// Add new Insurance

	@Override
	public void addInsurances(Insurance insurance) {
		
		// Failure - Duplicate Entries
		
		for (Insurance ins : list) {
				if ((ins.getCustomer_id().equalsIgnoreCase(insurance.getCustomer_id()))
					&& (ins.getInsurance_name().equalsIgnoreCase(insurance.getInsurance_name()))
					&& (ins.getInsurance_type().equalsIgnoreCase(insurance.getInsurance_type())))
				
				throw new DuplicateEntry();
		}
		
		// Failure - Query Parameters Missing
		
//		for (Insurance ins : list) {
		if ((insurance.getCustomer_id()==null || insurance.getCustomer_id().length()==0))

			throw new MissingQueryParam();
//	}
		// Success - Success message for adding Insurance details in json format
		
		if(insurance.getCustomer_id().startsWith("cus")) {
			list.add(insurance);
		} else {
			
			// Different Queries are being passed
			
			throw new DifferentQueryParam();
		}
		
		
		
	}

	// Update existing Insurance
	
	@Override
	public void updateInsurances(Insurance insurance) {
		
		// Duplicate Entry - Error Scenario - EXTRA
		
		for (Insurance ins : list) {
			if (ins.getInsurance_id().equalsIgnoreCase(insurance.getInsurance_id())
					&& (ins.getCustomer_id().equalsIgnoreCase(insurance.getCustomer_id()))
					&& (ins.getInsurance_name().equalsIgnoreCase(insurance.getInsurance_name()))
					&& (ins.getInsurance_type().equalsIgnoreCase(insurance.getInsurance_type())))
				throw new DuplicateEntry();
		}

		// Missing Query Parameters
		
		if ((insurance.getInsurance_id().equalsIgnoreCase("")) || (insurance.getCustomer_id()==null || insurance.getCustomer_id().length()==0))

			throw new MissingQueryParam();
		
		if((!(insurance.getCustomer_id().startsWith("cus"))) || (insurance.getInsurance_id().length()!=13)) {
			
			throw new DifferentQueryParam();
		}
		
		// Success - Success message of updating Insurance details in json format
		
		for (Insurance ins : list) {
			if (ins.getInsurance_id().equalsIgnoreCase(insurance.getInsurance_id())) {
				ins.setCustomer_id(insurance.getCustomer_id());
				if((insurance.getInsurance_name()==null) || (insurance.getInsurance_name().length()==0))
					ins.setInsurance_name("");
				else
					ins.setInsurance_name(insurance.getInsurance_name());
				if((insurance.getInsurance_type()==null) || (insurance.getInsurance_type().length()==0))
					ins.setInsurance_type("");
				else
					ins.setInsurance_type(insurance.getInsurance_type());
				break;
			}
			
//			else {
//				throw new MissingQueryParam();
//			}
			
		}
		
//		for (Insurance ins : list) {
//			if (ins.getCustomer_id().equalsIgnoreCase(insurance.getCustomer_id())) {
//				ins.setInsurance_id(insurance.getInsurance_id());
//				ins.setInsurance_name(insurance.getInsurance_name());
//				ins.setInsurance_type(insurance.getInsurance_type());
//				break;
//			}
//		}
		
	}
	
	// GET Insurances

	
	// CUSTOMER ID
	@Override
	public Insurance findInsuranceByCId(String cId) {
		if(cId.startsWith("cus")) {
			for (Insurance ins : list) {
				if (ins.getCustomer_id().equalsIgnoreCase(cId))
					return ins;
				else
					throw new NoInsuranceForCustomerId();
			}
		} else {
			throw new DifferentQueryParam();
		}
		
		return null;
	}
	
	// INSURANCE ID
	@Override
	public Insurance findInsuranceByIId(String iId) {
		if(iId.length()!=13) {
			throw new DifferentQueryParam();
		}
		for (Insurance ins : list) {
			if (ins.getInsurance_id().equalsIgnoreCase(iId))
				return ins;
		}
		return null;
	}

	// BOTH CUSTOMER ID AND INSURANCE ID
	@Override
	public Insurance findInsuranceByIIdandCId(String iId, String cId) {
		if(cId.startsWith("cus")&&(iId.length()==13)) {
			for (Insurance ins : list) {
				if ((ins.getInsurance_id().equalsIgnoreCase(iId))&&(ins.getCustomer_id().equalsIgnoreCase(cId)))
					return ins;
			}
		} else {
			throw new DifferentQueryParam();
		}
		return null;
	}

//	@Override
//	public Insurance noQueryParams() {
//		return null;
//	}

}
