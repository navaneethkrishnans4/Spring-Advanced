package com.bej.authentication.controller;

import com.bej.authentication.domain.Customer;
import com.bej.authentication.exception.CustomerAlreadyExistsException;
import com.bej.authentication.service.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;
    private Customer customer1;



    @BeforeEach
    void setUp() {

        customer1 = new Customer(1001,"Johny123");


        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @AfterEach
    void tearDown() {
        customer1=null;

    }
    @Test
    public void givenUserToSaveReturnUserSuccess() throws Exception {
        when(customerService.saveCustomer(any())).thenReturn(customer1);
        mockMvc.perform(post("/api/v2/saveCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer1)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(customerService,times(1)).saveCustomer(any());

    }
    @Test
    public void givenUserToSaveReturnUserFailure() throws Exception, CustomerAlreadyExistsException {
        when(customerService.saveCustomer(any())).thenThrow(CustomerAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/saveCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer1)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }
}