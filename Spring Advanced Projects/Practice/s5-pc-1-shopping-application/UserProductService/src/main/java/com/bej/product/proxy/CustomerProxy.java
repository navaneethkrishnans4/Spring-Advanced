package com.bej.product.proxy;


import com.bej.product.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="customer-authentication-service",url="localhost:8087")
public interface CustomerProxy {
    @PostMapping("/api/v2/saveCustomer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer);
}
