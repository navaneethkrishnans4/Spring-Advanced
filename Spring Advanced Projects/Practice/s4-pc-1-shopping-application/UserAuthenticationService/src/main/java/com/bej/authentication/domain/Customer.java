package com.bej.authentication.domain;

import javax.persistence.Entity;
import javax.persistence.Id;


//Annotate the class with @Entity
@Entity
public class Customer {
    @Id
    private int customerId;
    private String customerPassword;


    public Customer() {
    }

    public Customer(int customerId, String customerPassword) {
        this.customerId = customerId;
        this.customerPassword = customerPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerPassword='" + customerPassword + '\'' +
                '}';
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
}
