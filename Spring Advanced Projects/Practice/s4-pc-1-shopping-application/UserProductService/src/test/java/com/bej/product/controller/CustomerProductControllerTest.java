package com.bej.product.controller;


import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.service.CustomerProductServiceImpl;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CustomerProductServiceImpl customerProductService;
    private Product product1,product2;
    private Customer customer;
    List<Product> productList;

    @InjectMocks
    private CustomerProductController customerProductController;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(customerProductController).build();
        product1 = new Product();
        product1.setProductCode("P001");
        product1.setProductName("Furniture");
        product1.setSpecifications("Garden Chair 31 X 21 X 14");
        product1.setStock(true);
        product1.setPrice(4000);
        product1.setDiscountedPrice(3500);

        product2 = new Product();
        product2.setProductCode("P002");
        product2.setProductName("Tea Table");
        product2.setSpecifications("Table 31 X 21 X 14");
        product2.setStock(true);
        product2.setPrice(4000);
        product2.setDiscountedPrice(3500);

        productList = Arrays.asList(product1, product2);

        customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerEmail("sam@gmail.com");
        customer.setCustomerName("Sammy");
        customer.setCustomerPassword("1234");
    }

    @AfterEach
    public void tearDown()
    {
        product1 = null;
        product2 = null;
        productList = null;
        customer=null;
    }
    @Test
    public void registerUserSuccess() throws Exception {
        when(customerProductService.registerCustomer(any())).thenReturn(customer);
        mockMvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJSONString(customer)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void registerUserFailure() throws Exception {
        when(customerProductService.registerCustomer(any())).thenThrow(CustomerAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJSONString(customer)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJSONString(Object user) {
        try {
            return new ObjectMapper().writeValueAsString(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}