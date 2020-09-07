package com.cts.insurance.service;

import java.util.List;

import com.cts.insurance.dto.Insurance;

public interface Insurance_Service {
	
	public List<Insurance> getAllInsurances();

	public Insurance addInsurances(Insurance insurance);

	public Insurance updateInsurances(Insurance insurance);

	public Insurance findInsuranceByCId(String cid);

	public Insurance findInsuranceByIId(String iId);

	public Insurance findInsuranceByIIdandCId(String iId, String cId);

	public Insurance throwMissing(String iId, String cId);
	
}
