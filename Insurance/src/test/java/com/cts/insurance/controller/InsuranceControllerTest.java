package com.cts.insurance.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.insurance.dto.Insurance;
import com.cts.insurance.service.Insurance_ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(InsuranceController.class)
class InsuranceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Insurance_ServiceImpl service;

	@Test
	public void testCreateInsurance() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/service/add")
				.content(mapToJson(new Insurance("cus1234", "INSNAME1", "car"))).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testCreateInsuranceMiss() throws Exception {
		Insurance mockInsurance = new Insurance("", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1234567898765");
		String inputInJson = this.mapToJson(mockInsurance);
		String URI = "/insurance/service/add";
		Mockito.when(service.addInsurances(mockInsurance)).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = null;
		try {
			outputInJson = response.getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assertThat(outputInJson).isNotEqualTo(inputInJson);
	}

	@Test
	public void testCreateInsuranceDiff() throws Exception {
		Insurance mockInsurance = new Insurance("1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1234567898765");
		String inputInJson = this.mapToJson(mockInsurance);
		String URI = "/insurance/service/add";
		Mockito.when(service.addInsurances(mockInsurance)).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = null;
		try {
			outputInJson = response.getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assertThat(outputInJson).isNotEqualTo(inputInJson);
	}

	@Test
	public void testCreateInsuranceDuplicateEntry() throws Exception {

		Insurance mockInsurance = new Insurance("1234", "INSNAME1", "car");
		Insurance mockInsurance2 = new Insurance("1234", "INSNAME1", "car");
		String inputInJson = this.mapToJson(mockInsurance2);
		String URI = "/insurance/service/add";
		Mockito.when(service.addInsurances(mockInsurance)).thenReturn(mockInsurance);
		Mockito.when(service.addInsurances(mockInsurance2)).thenReturn(mockInsurance2);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = null;
		try {
			outputInJson = response.getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assertThat(outputInJson).isNotEqualTo(inputInJson);
	}

	// Update Testing

	@Test
	public void testUpdateInsurance() throws Exception {
		Insurance insurance = new Insurance("cus1234", "INSNAME1", "car");
		insurance.setInsurance_id("1234567898765");
		service.addInsurances(insurance);
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME2", "cycle");
		mockInsurance.setInsurance_id("1234567898765");
		mockMvc.perform(MockMvcRequestBuilders.put("/insurance/service/update").content(mapToJson(mockInsurance))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testUpdateInsuranceMiss() throws Exception {
		String URI = "/insurance/service/update";
		Insurance insurance = new Insurance("cus1234", "INSNAME1", "car");
		insurance.setInsurance_id("1234567898765");
		service.addInsurances(insurance);
		Insurance mockInsurance = new Insurance("", "INSNAME2", "cycle");
		mockInsurance.setInsurance_id("1234567898765");
		String inputInJson = this.mapToJson(mockInsurance);
		Mockito.when(service.updateInsurances(Mockito.any(Insurance.class))).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = null;
		try {
			outputInJson = response.getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assertThat(outputInJson).isNotEqualTo(inputInJson);
	}

	@Test
	public void testUpdateInsuranceDiff() throws Exception {
		String URI = "/insurance/service/update";
		Insurance insurance = new Insurance("cus1234", "INSNAME1", "car");
		insurance.setInsurance_id("1234567898765");
		service.addInsurances(insurance);
		Insurance mockInsurance = new Insurance("1234", "INSNAME2", "cycle");
		mockInsurance.setInsurance_id("1234567898765");
		String inputInJson = this.mapToJson(mockInsurance);
		Mockito.when(service.updateInsurances(Mockito.any(Insurance.class))).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = null;
		try {
			outputInJson = response.getContentAsString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assertThat(outputInJson).isNotEqualTo(inputInJson);
	}

	// Success - Insurance Details need to be displayed based on Customer Id

	@Test
	public void testfindInsuranceByCId() throws Exception {

		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		String URI = "/insurance/service/get?customer_id=cus1234";
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByCIdMissingQueryParam() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		String URI = "/insurance/service/get?customer_id=";
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByCIdDifferentQueryParam() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		String URI = "/insurance/service/get?customer_id=1234";
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByCIdNoInsuranceForCustomerId() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		String URI = "/insurance/service/get?customer_id=cus987";
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	// Success - Insurance Details need to be displayed based on Insurance Id

	@Test
	public void testfindInsuranceByIId() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1524789891234");
		String URI = "/insurance/service/get?insurance_id=1524789891234";
		Mockito.when(service.findInsuranceByIId("1524789891234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByIIdMissingQueryParam() throws Exception {

		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1524789891234");

		String URI = "/insurance/service/get?insurance_id=";

		Mockito.when(service.findInsuranceByIId("1524789891234")).thenReturn(mockInsurance);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByIIdDifferentQueryParam() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1524789891234");
		String URI = "/insurance/service/get?insurance_id=152478989";
		Mockito.when(service.findInsuranceByIId("1524789891234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	// Success - Insurance Details need to be displayed based on Insurance Id and
	// Customer Id

	@Test
	public void testfindInsuranceByIIdandCId() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1524789891234");
		String URI = "/insurance/service/get?insurance_id=1524789891234&customer_id=cus1234";
		Mockito.when(service.findInsuranceByIIdandCId("1524789891234", "cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByIIdandCIdMissingQueryParameter() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1524789891234");
		String URI = "/insurance/service/get?insurance_id=&customer_id=";
		Mockito.when(service.findInsuranceByIIdandCId("1524789891234", "cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	@Test
	public void testfindInsuranceByIIdandCIdDifferentParameter() throws Exception {
		Insurance mockInsurance = new Insurance("cus1234", "INSNAME1", "car");
		mockInsurance.setInsurance_id("1524789891234");
		String URI = "/insurance/service/get?insurance_id=cus1234&customer_id=1524789891234";
		Mockito.when(service.findInsuranceByIIdandCId("1524789891234", "cus1234")).thenReturn(mockInsurance);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
