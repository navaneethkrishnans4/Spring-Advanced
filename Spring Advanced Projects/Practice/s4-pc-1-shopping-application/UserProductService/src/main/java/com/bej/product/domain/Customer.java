package com.bej.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//Annotate the class with @Document
@Document
public class Customer {

    //Annotate the customerId with @Id
@Id
    private int customerId;
    private String customerPassword;
    private String customerName;
    private String customerEmail;
    private List<Product> productList;

    public Customer() {
    }

    public Customer(int customerId, String customerPassword, String customerName, String customerEmail, List<Product> productList) {
        this.customerId = customerId;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.productList = productList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerPassword=" + customerPassword +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", productList=" + productList +
                '}';
    }
}
