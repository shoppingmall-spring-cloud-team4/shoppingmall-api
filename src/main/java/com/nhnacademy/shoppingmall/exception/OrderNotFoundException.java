package com.nhnacademy.shoppingmall.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer orderId) {
        super("order not found: id=" + orderId);
    }

}
