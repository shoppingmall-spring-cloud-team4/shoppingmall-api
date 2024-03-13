package com.nhnacademy.shoppingmall.exception;

public class UserPointNotEnoughException extends RuntimeException{
    public UserPointNotEnoughException(Integer userPoint) {
        super("userPoint not enough: userPoint=" + userPoint);
    }
}
