package com.bej.authentication.repository;

import com.bej.authentication.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCustomerIdAndCustomerPassword(int customerId, String password);
}
