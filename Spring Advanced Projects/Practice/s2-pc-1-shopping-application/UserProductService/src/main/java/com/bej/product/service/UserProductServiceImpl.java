package com.bej.product.service;

import com.bej.product.domain.User;
import com.bej.product.domain.Product;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserProductServiceImpl implements IUserProductService {

    // Autowire the UserProductRepository using constructor autowiring
    private UserProductRepository userProductRepository;

    @Autowired
    public UserProductServiceImpl(UserProductRepository userProductRepository) {
        this.userProductRepository = userProductRepository;
    }
    @Override
    public User registerUser(User user)  throws UserAlreadyExistsException{
        // Register a new user
        if (userProductRepository.findById(user.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userProductRepository.save(user);

    }

    @Override
    public User saveUserProductToList(Product product, String userId) throws  UserNotFoundException{
        // Save the product to the User
        Optional<User> optionalUser = userProductRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Product> productList = user.getProductList();
            // Check if productList is null and initialize it with an empty list if necessary
            if (productList == null) {
                productList = new ArrayList<>();
                user.setProductList(productList);
            }
            productList.add(product);
            user.setProductList(productList);
            return userProductRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User deleteUserProductFromList(String userId, String productCode)  throws UserNotFoundException{
        // Delete a product from the user list
        Optional<User> optionalUser = userProductRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Product> productList = user.getProductList();
            productList.removeIf(product -> product.getProductCode().equals(productCode));
            user.setProductList(productList);
            return userProductRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Product> getAllUserProductsFromList(String userId) throws UserNotFoundException{
        // Get all products from the User list
        Optional<User> optionalUser = userProductRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getProductList();
        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public User getUserByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
        // Validate for wrong credentials
        User user = userProductRepository.findByUserIdAndPassword(userId, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }




}
