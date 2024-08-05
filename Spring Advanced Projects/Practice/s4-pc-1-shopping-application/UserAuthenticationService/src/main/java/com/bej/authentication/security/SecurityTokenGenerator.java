package com.bej.authentication.security;



import com.bej.authentication.domain.Customer;


public interface SecurityTokenGenerator {
    String createToken(Customer user);
}
