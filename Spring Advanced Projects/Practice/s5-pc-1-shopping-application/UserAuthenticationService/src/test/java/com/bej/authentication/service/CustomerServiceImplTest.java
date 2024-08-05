package com.bej.authentication.service;

import com.bej.authentication.domain.Customer;
import com.bej.authentication.exception.CustomerAlreadyExistsException;
import com.bej.authentication.repository.CustomerRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1;
    private Customer customer2;


    @BeforeEach
    void setUp() {

        customer1 = new Customer(1001,"Johny123");

        customer2 = new Customer(1002,"Harry123");

    }
    @AfterEach
    void tearDown() {
        customer1=null;
        customer2 = null;
    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomerSuccess() throws CustomerAlreadyExistsException {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        when(customerRepository.save(any())).thenReturn(customer1);
        assertEquals(customer1,customerService.saveCustomer(customer1));
        verify(customerRepository,times(1)).save(any());
        verify(customerRepository,times(1)).findById(any());
    }
    @Test
    public void givenUserToSaveReturnSavedUserFailure() throws CustomerAlreadyExistsException {
        when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(customer1));
        assertThrows(CustomerAlreadyExistsException.class,()->customerService.saveCustomer(customer1));
        verify(customerRepository,times(1)).findById(any());
        verify(customerRepository,times(0)).save(any());
    }
    @Test
    public void givenUserLoginSuccessReturnRetrievedUser()
    {
        when(customerRepository.findByCustomerIdAndCustomerPassword(anyInt(),any())).thenReturn(customer1);

        assertEquals(customer1,customerRepository.findByCustomerIdAndCustomerPassword(customer1.getCustomerId(),customer1.getCustomerPassword()));
        verify(customerRepository,times(1)).findByCustomerIdAndCustomerPassword(anyInt(),any());
    }

}