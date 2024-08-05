package com.bej.product.repository;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@DataMongoTest
class CustomerProductRepositoryTest {
    @Autowired
    private CustomerProductRepository customerProductRepository;
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
        customer.setCustomerEmail("sam@gmail.com");
        customer.setCustomerId(1234);
        customer.setCustomerPassword("Spass");
        customer.setCustomerName("John");
        customer.setProductList(productList);

    }

    @AfterEach
    public void tearDown()
    {
        customerProductRepository.deleteAll();
    }
    @Test
    public void registerCustomerSuccess()
    {
        customerProductRepository.insert(customer);
        Customer customer1 = customerProductRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustomerId(),customer1.getCustomerId());
    }
    @Test
    public void getProductsByUserIDSuccess()
    {
        customerProductRepository.insert(customer);
        Customer customer1 = customerProductRepository.findById(customer.getCustomerId()).get();
        List<Product> list = customer1.getProductList();
        assertEquals(2,list.size());
    }
    @Test
    public void deleteProductByUserIDSuccess()
    {
        customerProductRepository.insert(customer);
        Customer customer1 = customerProductRepository.findById(customer.getCustomerId()).get();
        List<Product> list = customer1.getProductList();
        list.removeIf(r->r.getProductCode().equals("P001"));
        assertEquals(1,list.size());
    }
}