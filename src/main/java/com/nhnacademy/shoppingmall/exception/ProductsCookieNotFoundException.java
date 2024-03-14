package com.nhnacademy.shoppingmall.exception;

public class ProductsCookieNotFoundException extends RuntimeException{
    public ProductsCookieNotFoundException()
    {
        super("Products 쿠키가 존재하지 않습니다.");
    }
}
