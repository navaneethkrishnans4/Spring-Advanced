package com.bej.authentication.service;

import com.bej.authentication.domain.Customer;
import com.bej.authentication.exception.CustomerAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;

public interface ICustomerService {
    Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
    Customer getCustomerByCustomerIdAndPassword(int customerId, String password) throws InvalidCredentialsException;
}
