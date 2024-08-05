package com.bej.authentication.service;

import com.bej.authentication.domain.Customer;
import com.bej.authentication.exception.CustomerAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements  ICustomerService{

    // Autowire the CustomerRepository using constructor autowiring

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
        //save the customer in the db
        if (customerRepository.existsById(customer.getCustomerId())) {
            throw new CustomerAlreadyExistsException();
        }
        // Save the customer
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByCustomerIdAndPassword(int customerId, String password) throws InvalidCredentialsException {
        //validate the customer

        Customer customer = customerRepository.findByCustomerIdAndCustomerPassword(customerId, password);
        if (customer == null) {
            throw new InvalidCredentialsException();
        }
        return customer;
    }

}
