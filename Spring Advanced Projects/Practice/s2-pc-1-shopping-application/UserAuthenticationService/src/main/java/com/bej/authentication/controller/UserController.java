package com.bej.authentication.controller;

import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.service.IUserService;
import com.bej.authentication.domain.User;
import com.bej.authentication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    // Autowire the IUserService, SecurityTokenGenerator using constructor autowiring
    private final UserServiceImpl userService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserServiceImpl userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/user")
    public ResponseEntity<?> saveCustomer(@RequestBody User user){
        // Write the logic to save a user,
        // return 201 status if user is saved else 500 status
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        // Generate the token on login,
        // return 200 status if user is saved else 500 status
        try {
            User retrievedUser = userService.getUserByUserIdAndPassword(user.getUserId(), user.getPassword());
            if (retrievedUser == null) {
                throw new InvalidCredentialsException();
            }
            String token = securityTokenGenerator.createToken(user);
            return ResponseEntity.ok(token);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
