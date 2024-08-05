package com.bej.product.domain;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String productCode;
    private String productName;
    private double price;
    private double discountedPrice;
    private String specifications;
    private boolean stock;

    public Product() {
    }

    public Product(String productCode, String productName, double price, double discountedPrice, String specifications, boolean stock) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.specifications = specifications;
        this.stock = stock;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", discountedPrice=" + discountedPrice +
                ", specifications='" + specifications + '\'' +
                ", stock=" + stock +
                '}';
    }
}
