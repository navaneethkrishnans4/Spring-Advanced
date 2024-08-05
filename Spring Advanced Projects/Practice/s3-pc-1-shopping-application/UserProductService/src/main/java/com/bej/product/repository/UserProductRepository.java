package com.bej.product.repository;
import com.bej.product.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserProductRepository extends MongoRepository<User, String> {

}
