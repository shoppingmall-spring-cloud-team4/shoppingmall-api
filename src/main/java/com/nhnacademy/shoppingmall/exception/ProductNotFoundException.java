package com.nhnacademy.shoppingmall.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer productId) {
        super("product not found: id=" + productId);
    }

}
