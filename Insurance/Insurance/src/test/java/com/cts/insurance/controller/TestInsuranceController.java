package com.cts.insurance.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.insurance.dto.Insurance;
import com.cts.insurance.service.Insurance_ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(InsuranceController.class)
class TestInsuranceController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Insurance_ServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Insurance> insuranceList;

    @BeforeEach
    void setUp() {
        this.insuranceList = new ArrayList<>();
        this.insuranceList.add(new Insurance("cus3000","INS02","car"));
        this.insuranceList.add(new Insurance("cus2000","INS01","house"));
        this.insuranceList.add(new Insurance("cus1000","INS03","cricket"));
    }

    @Test
    void shouldFetchAllInsurances() throws Exception {

        when(service.getAllInsurances()).thenReturn(insuranceList);

        this.mockMvc.perform(get("/insurance/service/insurances"))
                .andExpect(status().isOk());
    }

//    @Test
//    void shouldFetchOneInsuranceById() throws Exception {
//        final Long InsuranceId = 1L;
//        final Insurance Insurance = new Insurance(1L, "ten@mail.com","teten","teten");
//
//        given(service.findInsuranceById(InsuranceId)).willReturn(Optional.of(Insurance));
//
//        this.mockMvc.perform(get("/api/Insurances/{id}", InsuranceId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email", is(Insurance.getEmail())))
//                .andExpect(jsonPath("$.name", is(Insurance.getName())));
//    }

//    @Test
//    void shouldReturn404WhenFindInsuranceById() throws Exception {
//        final Long InsuranceId = 1L;
//        given(service.findInsuranceById(InsuranceId)).willReturn(Optional.empty());
//
//        this.mockMvc.perform(get("/api/Insurance/{id}", InsuranceId))
//                .andExpect(status().isNotFound());
//    }

    @Test
    void shouldCreateNewInsurance() throws Exception {
               
        mockMvc.perform(
      	      post("/insurance/service/add")
      	      .content(mapToJson(new Insurance("cus1234","INSNAME1","car")))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
      	      .andExpect(status().isOk());
    }

    
    @Test
    void shouldCreateNewInsuranceMissingParameter() throws Exception {
               
        mockMvc.perform(
      	      post("/insurance/service/add")
      	      .content(mapToJson(new Insurance("","INSNAME1","car")))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
      	      .andExpect(status().isOk());
    }
    
    @Test
    void shouldReturn400WhenCreateNewInsuranceWithoutCustomerId() throws Exception {
        Insurance insurance = new Insurance("","INSNAME1","car");

        this.mockMvc.perform(post("/insurance/service/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurance)))
                .andExpect(status().isBadRequest())
                .andReturn()
        ;
    }

    @Test
    void updateInsurance() throws Exception {
        Insurance insurance = new Insurance("cus3000","INS02","car");
        insurance.setInsurance_id("1259874632145");
        given(service.findInsuranceByIId(insurance.getInsurance_id())).willReturn(insurance);
        given(service.updateInsurances((insurance))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurance)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenUpdatingNonExistingInsurance() throws Exception {
    	Insurance insurance =  new Insurance("cus3000","INS02","car");
    	insurance.setInsurance_id("7539512584560");
        given(service.findInsuranceByIId("7539512584561")).willReturn(insurance);
        

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurance)))
                .andExpect(status().isBadRequest());

    }
    
    
    @Test
    void shouldReturn400WhenUpdatingMissingParameter() throws Exception {
    	Insurance insurance =  new Insurance("","INS02","car");
    	insurance.setInsurance_id("7539512584560");
        given(service.findInsuranceByIId("7539512584560")).willReturn(insurance);
        

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurance)))
                .andExpect(status().isBadRequest());

    }
    
    @Test
    void shouldReturn400WhenUpdatingDifferentParameter() throws Exception {
    	Insurance insurance =  new Insurance("7539512584564","INS02","car");
    	insurance.setInsurance_id("7539512584560");
        given(service.findInsuranceByIId("7539512584560")).willReturn(insurance);
        

        this.mockMvc.perform(put("/insurance/service/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(insurance)))
                .andExpect(status().isBadRequest());

    }


//    @Test
//    void shouldDeleteInsurance() throws Exception {
//        Long InsuranceId = 1L;
//        Insurance Insurance = new Insurance(InsuranceId, "Insurance1@gmail.com", "pwd", "Name");
//        given(service.findInsuranceById(InsuranceId)).willReturn(Optional.of(Insurance));
//        doNothing().when(service).deleteInsuranceById(Insurance.getId());
//
//        this.mockMvc.perform(delete("/api/Insurances/{id}", Insurance.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email", is(Insurance.getEmail())))
//                .andExpect(jsonPath("$.password", is(Insurance.getPassword())))
//                .andExpect(jsonPath("$.name", is(Insurance.getName())));
//
//    }
//
//    @Test
//    void shouldReturn404WhenDeletingNonExistingInsurance() throws Exception {
//        Long InsuranceId = 1L;
//        given(service.findInsuranceById(InsuranceId)).willReturn(Optional.empty());
//
//        this.mockMvc.perform(delete("/api/Insurances/{id}", InsuranceId))
//                .andExpect(status().isNotFound());
//        
//        
//
//    }
    
    private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
