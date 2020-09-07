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
	public void testCreateInsurance() throws Exception 
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .post("/insurance/service/add")
	      .content(mapToJson(new Insurance("cus1234","INSNAME1","car")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	
	@Test
	public void testCreateInsuranceMiss() throws Exception 
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .post("/insurance/service/add")
	      .content(mapToJson(new Insurance("","INSNAME1","car")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	
	@Test
	public void testCreateInsuranceDiff() throws Exception 
	{
	  mockMvc.perform( MockMvcRequestBuilders
	      .post("/insurance/service/add")
	      .content(mapToJson(new Insurance("123","INSNAME1","car")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}

	
	
	@Test
	public void testCreateInsuranceDuplicateEntry() throws Exception {

		Insurance mockInsurance = new Insurance("1234","INSNAME1","car");
		Insurance mockInsurance2 = new Insurance("1234","INSNAME1","car");

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
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	// Update Testing
	
    @Test
    void testUpdateInsurance() throws Exception {
        Insurance insurance = new Insurance("cus3000","INS02","car");
        insurance.setInsurance_id("1259874632145");
        given(service.findInsuranceByIId(insurance.getInsurance_id())).willReturn(insurance);
        given(service.updateInsurances((insurance))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(insurance)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateNonExistingInsurance() throws Exception {
    	Insurance insurance =  new Insurance("cus3000","INS02","car");
    	insurance.setInsurance_id("7539512584560");
        given(service.findInsuranceByIId("7539512584561")).willReturn(insurance);
        

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(insurance)))
                .andExpect(status().isBadRequest());

    }
    
    
    @Test
    void testUpdateMissingParameter() throws Exception {
    	Insurance insurance =  new Insurance("","INS02","car");
    	insurance.setInsurance_id("7539512584560");
        given(service.findInsuranceByIId("7539512584560")).willReturn(insurance);
        

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(insurance)))
                .andExpect(status().isBadRequest());

    }
    
    @Test
    void testUpdateDifferentParameter() throws Exception {
    	Insurance insurance =  new Insurance("7539512584564","INS02","car");
    	insurance.setInsurance_id("7539512584560");
        given(service.findInsuranceByIId("7539512584560")).willReturn(insurance);
        

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(insurance)))
                .andExpect(status().isBadRequest());

    }
	
	
	
	
	// Success -  Insurance Details need to be displayed based on Customer Id

	@Test
	public void testfindInsuranceByCId() throws Exception {
		
		Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
		
		String URI = "/insurance/service/get?customer_id=cus1234";
		
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
	}
	
	@Test
	public void testfindInsuranceByCIdMissingQueryParam() throws Exception {
		
		Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
		
		String URI = "/insurance/service/get?customer_id=";
		
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isNotEqualTo(expectedJson);
		
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	
	}
	
	@Test
	public void testfindInsuranceByCIdDifferentQueryParam() throws Exception {
		
		Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
		
		String URI = "/insurance/service/get?customer_id=1234";
		
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
	}
	
	@Test
	public void testfindInsuranceByCIdNoInsuranceForCustomerId() throws Exception {
		
		Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
		
		String URI = "/insurance/service/get?customer_id=cus987";
		
		Mockito.when(service.findInsuranceByCId("cus1234")).thenReturn(mockInsurance);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockInsurance);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
		
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	
	}
	
	// Success -  Insurance Details need to be displayed based on Insurance Id

		@Test
		public void testfindInsuranceByIId() throws Exception {
			
			Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
			mockInsurance.setInsurance_id("1524789891234");
			
			String URI = "/insurance/service/get?insurance_id=1524789891234";
			
			Mockito.when(service.findInsuranceByIId("1524789891234")).thenReturn(mockInsurance);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expectedJson = this.mapToJson(mockInsurance);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isEqualTo(expectedJson);
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		}
		
		@Test
		public void testfindInsuranceByIIdMissingQueryParam() throws Exception {
			
			Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
			mockInsurance.setInsurance_id("1524789891234");
			
			String URI = "/insurance/service/get?insurance_id=";
			
			Mockito.when(service.findInsuranceByIId("1524789891234")).thenReturn(mockInsurance);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expectedJson = this.mapToJson(mockInsurance);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isNotEqualTo(expectedJson);
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		
		}
		
		@Test
		public void testfindInsuranceByIIdDifferentQueryParam() throws Exception {
			
			Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
			mockInsurance.setInsurance_id("1524789891234");
			
			String URI = "/insurance/service/get?insurance_id=152478989";
			
			Mockito.when(service.findInsuranceByIId("1524789891234")).thenReturn(mockInsurance);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expectedJson = this.mapToJson(mockInsurance);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isNotEqualTo(expectedJson);
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		}
		
		// Success -  Insurance Details need to be displayed based on Insurance Id and Customer Id
		
		@Test
		public void testfindInsuranceByIIdandCId() throws Exception {
			
			Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
			mockInsurance.setInsurance_id("1524789891234");
			
			String URI = "/insurance/service/get?insurance_id=1524789891234&customer_id=cus1234";
			
			Mockito.when(service.findInsuranceByIIdandCId("1524789891234","cus1234")).thenReturn(mockInsurance);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expectedJson = this.mapToJson(mockInsurance);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isEqualTo(expectedJson);
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		}
				
		@Test
		public void testfindInsuranceByIIdandCIdMissingQueryParameter() throws Exception {
			
			Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
			mockInsurance.setInsurance_id("1524789891234");
			
			String URI = "/insurance/service/get?insurance_id=&customer_id=";
			
			Mockito.when(service.findInsuranceByIIdandCId("1524789891234","cus1234")).thenReturn(mockInsurance);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expectedJson = this.mapToJson(mockInsurance);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isNotEqualTo(expectedJson);
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		
		}
		
		@Test
		public void testfindInsuranceByIIdandCIdDifferentParameter() throws Exception {
			
			Insurance mockInsurance = new Insurance("cus1234","INSNAME1","car");
			mockInsurance.setInsurance_id("1524789891234");
			
			String URI = "/insurance/service/get?insurance_id=cus1234&customer_id=1524789891234";
			
			Mockito.when(service.findInsuranceByIIdandCId("1524789891234","cus1234")).thenReturn(mockInsurance);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expectedJson = this.mapToJson(mockInsurance);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isNotEqualTo(expectedJson);
			
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		}
		

	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
