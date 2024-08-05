package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.exception.CustomerNotFoundException;
import com.bej.product.exception.ProductNotFoundException;

import java.util.List;

public interface ICustomerProductService {
    Customer registerCustomer(Customer user) throws CustomerAlreadyExistsException;
    Customer saveCustomerProductToList(Product product, int customerId) throws CustomerNotFoundException;
    Customer deleteCustomerProductFromList(int customerId,String productCode) throws CustomerNotFoundException, ProductNotFoundException;
    List<Product> getAllUserProductsFromList(int customerId) throws CustomerNotFoundException;

}
