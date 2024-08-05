package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;

import com.bej.product.repository.CustomerProductRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CustomerProductServiceImplTest {
    @Mock
    CustomerProductRepository customerProductRepository;
    @InjectMocks
    CustomerProductServiceImpl customerProductService;
    private Product product1,product2;
    private Customer customer;
    List<Product> productList;
    @BeforeEach
    public void setUp() {

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
        customer.setCustomerId(12345);
        customer.setCustomerEmail("sam@gmail.com");
        customer.setCustomerName("John");
        customer.setCustomerPassword("John123");
        customer.setProductList(productList);
    }

    @AfterEach
    public void tearDown()
    {
        customer = null;
        product1 = null;
        product2 = null;
        productList = null;
    }

    @Test
    public void getAllUserTracksFromWishListSuccess() throws Exception {
        when(customerProductRepository.findById(anyInt())).thenReturn(Optional.ofNullable(customer));
        when(customerProductRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        assertEquals(productList,customerProductService.getAllUserProductsFromList(customer.getCustomerId()));
        verify(customerProductRepository,times(2)).findById(anyInt());

    }



}