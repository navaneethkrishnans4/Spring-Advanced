package com.bej.product.controller;

import com.bej.product.domain.User;
import com.bej.product.domain.Product;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.filter.JwtFilter;
import com.bej.product.service.IUserProductService;
import com.bej.product.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserProductController {

    private final IUserProductService userProductService;
    private final JwtUtil jwtUtil;
    // Autowire IUserProductService using constructor autowiring
    @Autowired
    public UserProductController(IUserProductService userProductService, JwtUtil jwtUtil) {
        this.userProductService = userProductService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody User user) {
        // Register a new user and save to db,
        // return 201 status if user is saved else 500 status
        try {
            User registeredUser = userProductService.registerUser(user);
            String token = jwtUtil.createToken(registeredUser); // Check if jwtUtil is null
            return ResponseEntity.status(HttpStatus.CREATED).header("Authorization", "Bearer " + token).body(registeredUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }

    @PostMapping("/customer/saveProduct")
    public ResponseEntity<?> saveCustomerProductToList(@RequestBody Product product, HttpServletRequest request) {
        // add a product to a specific customer,
        // return 201 status if track is saved else 500 status
        String userId = getCustomerIdFromClaims(request);
        try {
            User savedUser = userProductService.saveUserProductToList(product, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
        }
    }

    @GetMapping("/customer/getAllProducts")
    public ResponseEntity<?> getAllCustomerProductsFromList(HttpServletRequest request)  {
        // list all products of a specific customer,
        // return 200 status if track is saved else 500 status
        String userId = getCustomerIdFromClaims(request);
        try {
            return ResponseEntity.ok(userProductService.getAllUserProductsFromList(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
        }
    }

    @DeleteMapping("/customer/{productCode}")
    public ResponseEntity<?> deleteCustomerProductFromList(@PathVariable String productCode,HttpServletRequest request) throws ProductNotFoundException  {
        // delete product of a specific customer,
        // return 200 status if track is saved else 500 status
        String userId = getCustomerIdFromClaims(request);
        try {
            User updatedUser = userProductService.deleteUserProductFromList(userId, productCode);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
        }
    }

    // Read the customer id present in the claims from the request
    private String getCustomerIdFromClaims(HttpServletRequest request){
        String token = JwtFilter.extractTokenFromAuthorizationHeader(request.getHeader("Authorization"));

        // Parse the token to get the claims
        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();

        // Extract and return the customer ID from the claims
        return (String) claims.get("userId");
    }


}
