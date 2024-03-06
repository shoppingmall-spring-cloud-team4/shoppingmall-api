package com.nhnacademy.shoppingmall.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(Integer categoryId) {
        super("category not found: id=" + categoryId);
    }

}
