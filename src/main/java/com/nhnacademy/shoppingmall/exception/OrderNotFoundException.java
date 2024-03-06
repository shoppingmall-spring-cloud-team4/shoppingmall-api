package com.nhnacademy.shoppingmall.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Integer orderId) {
        super("order not found: id=" + orderId);
    }

}
