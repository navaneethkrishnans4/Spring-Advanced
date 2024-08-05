package com.bej.product.controller;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.exception.CustomerNotFoundException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.service.ICustomerProductService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class CustomerProductController {
    // Autowire ICustomerProductService using constructor autowiring
    private final ICustomerProductService customerProductService;

    @Autowired
    public CustomerProductController(ICustomerProductService customerProductService) {
        this.customerProductService = customerProductService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        // Register a new user and save to db,
        // return 201 status if user is saved else 500 status
        try {
            Customer savedCustomer = customerProductService.registerCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<>("Customer already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customer/saveProduct/{customerId}")
    public ResponseEntity<?> saveCustomerProductToList(@RequestBody Product product, @PathVariable int customerId) throws CustomerNotFoundException {
        // add a product to a specific customer,
        // return 201 status if product is saved else 500 status

        try {
            Customer updatedCustomer = customerProductService.saveCustomerProductToList(product, customerId);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/getAllProducts/{customerId}")
    public ResponseEntity<?> getAllCustomerProductsFromList(@PathVariable int customerId) {
        try {
            return new ResponseEntity<>(customerProductService.getAllUserProductsFromList(customerId), HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{customerId}/product/{productCode}")
    public ResponseEntity<?> deleteCustomerProductFromList(@PathVariable int customerId, @PathVariable String productCode) {
        try {
            Customer updatedCustomer = customerProductService.deleteCustomerProductFromList(customerId, productCode);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }
}
