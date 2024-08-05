package com.bej.userauthenticationservice.repository;


import com.bej.userauthenticationservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
User findByEmailAndPassword(String email, String password);
}
