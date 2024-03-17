package com.nhnacademy.shoppingmall.exception;

public class UserPointNotEnoughException extends RuntimeException{
    public UserPointNotEnoughException(Integer point) {
        super("userPoint not enough: current point=" + point);
    }
}
