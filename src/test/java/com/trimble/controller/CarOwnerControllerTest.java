package com.trimble.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimble.entities.CarOwner;
import com.trimble.service.CarOwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(controllers = CarOwnerController.class)
class CarOwnerControllerTest {

    @MockBean
    private CarOwnerService carOwnerService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerOwnerShouldRegisterCarOwner() throws Exception {
        // Given - Input CarOwner
        CarOwner inputOwner = new CarOwner();
        inputOwner.setOwnerId(1L);
        inputOwner.setName("John Doe");
        inputOwner.setEmail("john@example.com");
        inputOwner.setCars(Collections.emptyList()); // No cars for now

        // Expected saved owner (simulating DB-generated ID)
        CarOwner savedOwner = new CarOwner();
        savedOwner.setOwnerId(1L);
        savedOwner.setName("John Doe");
        savedOwner.setEmail("john@example.com");
        savedOwner.setCars(Collections.emptyList());
        Mockito.when(carOwnerService.registerOwner(any(CarOwner.class))).thenReturn(savedOwner);
         String s = mapper.writeValueAsString(inputOwner);
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/api/car-owner/register")
                 .contentType(MediaType.APPLICATION_JSON)
                .content(s);
        MvcResult mvcResult = mockMvc.perform(post).andReturn();
         MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(201,status);

    }
}