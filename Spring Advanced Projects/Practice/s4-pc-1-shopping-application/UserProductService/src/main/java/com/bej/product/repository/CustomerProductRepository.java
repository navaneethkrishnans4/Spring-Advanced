package com.bej.product.repository;

import com.bej.product.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerProductRepository extends MongoRepository<Customer, Integer> {

}
