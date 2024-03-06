package com.nhnacademy.shoppingmall.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(Integer productId) {
        super("product not found: id=" + productId);
    }

}
