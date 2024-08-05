package com.bej.authentication.repository;

import com.bej.authentication.domain.Customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        customer = new Customer(1234, "111");
    }
    @AfterEach
    public void tearDown() throws Exception {
        customer = null;
    }
    @Test
    public void testSaveUserSuccess() {
        customerRepository.save(customer);
        Customer object = customerRepository.findById(customer.getCustomerId()).get();
        assertEquals(customer.getCustomerId(), object.getCustomerId());
    }

    @Test
    public void testLoginUserSuccess() {
        customerRepository.save(customer);
        Customer object = customerRepository.findById(customer.getCustomerId()).get();
        assertEquals(customer.getCustomerId(), object.getCustomerId());
    }

}