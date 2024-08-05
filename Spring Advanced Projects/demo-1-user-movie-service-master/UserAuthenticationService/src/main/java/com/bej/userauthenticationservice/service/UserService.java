package com.bej.userauthenticationservice.service;


import com.bej.userauthenticationservice.domain.User;
import com.bej.userauthenticationservice.exception.UserAlreadyExistsException;
import com.bej.userauthenticationservice.exception.InvalidCredentialsException;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;
    //user name and pwd is in db ot not, if not save
    User findByEmailAndPassword(String email,String password) throws InvalidCredentialsException;



}
