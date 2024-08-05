package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.exception.CustomerNotFoundException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.proxy.CustomerProxy;
import com.bej.product.repository.CustomerProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerProductServiceImpl implements ICustomerProductService{
    // Autowire the CustomerProductRepository using constructor autowiring
    private final CustomerProductRepository customerProductRepository;
    private CustomerProxy customerProxy;

    @Autowired
    public CustomerProductServiceImpl(CustomerProxy customerProxy,CustomerProductRepository customerProductRepository) {
        this.customerProductRepository = customerProductRepository;
        this.customerProxy = customerProxy;
    }
    @Override
    public Customer registerCustomer(Customer customer) throws CustomerAlreadyExistsException {
        // Register a new user
        if (customerProductRepository.findById(customer.getCustomerId()).isPresent()) {
            throw new CustomerAlreadyExistsException();
        }
        Customer customer1 = customerProductRepository.save(customer);
        if(customer1.getCustomerId()!=0)
        {
            ResponseEntity r = customerProxy.saveCustomer(customer);
            System.out.println(r.getBody());
        }
        return customer1;
    }



    @Override
    public Customer saveCustomerProductToList(Product product, int customerId) throws CustomerNotFoundException {
        // Save the product to the customer list
        Customer customer = customerProductRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        List<Product> productList = customer.getProductList();
        productList.add(product);
        customer.setProductList(productList);
        return customerProductRepository.save(customer);
    }

    @Override
    public Customer deleteCustomerProductFromList(int customerId, String productCode) throws CustomerNotFoundException, ProductNotFoundException {
        // Delete a product from the customer list
        Optional<Customer> optionalUser = customerProductRepository.findById(customerId);
        if (optionalUser.isPresent()) {
            Customer user = optionalUser.get();
            List<Product> productList = user.getProductList();
            productList.removeIf(product -> product.getProductCode().equals(productCode));
            user.setProductList(productList);
            return customerProductRepository.save(user);
        } else {
            throw new CustomerNotFoundException();
        }

    }

    @Override
    public List<Product> getAllUserProductsFromList(int customerId) throws CustomerNotFoundException {
        // Get all products from the customer list
        Customer customer = customerProductRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        return customer.getProductList();

    }


}
