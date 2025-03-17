package com.trimble.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimble.entities.Admin;
import com.trimble.service.AdminService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(controllers =AdminController.class )
class AdminControllerTest {

    @Mock
    private AdminService adminService;
@Autowired
private ObjectMapper objectMapper;
    @Autowired
   private MockMvc mockMvc;


    @Test
    void registerAdmin_Success() throws Exception {
        Admin admin=new Admin(1l,"admin","abc01");
        when(adminService.registerAdmin(any(Admin.class))).thenReturn(admin);

         String adminObj = objectMapper.writeValueAsString(admin);
         MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/api/admin/register")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(adminObj);
         MvcResult mvcResult = mockMvc.perform(postRequest).andReturn();
         MockHttpServletResponse response = mvcResult.getResponse();
         int status = response.getStatus();
         assertEquals(201,status);

    }




}
