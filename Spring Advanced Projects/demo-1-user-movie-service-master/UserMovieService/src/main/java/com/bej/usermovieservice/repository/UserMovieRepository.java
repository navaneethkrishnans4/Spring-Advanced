package com.bej.usermovieservice.repository;

import com.bej.usermovieservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMovieRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
