package com.nhnacademy.shoppingmall.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer categoryId) {
        super("category not found: id=" + categoryId);
    }

}
