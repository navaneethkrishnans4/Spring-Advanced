package com.bej.userauthenticationservice.security;



import com.bej.userauthenticationservice.domain.User;

public interface SecurityTokenGenerator {
    String createToken(User user);
}
