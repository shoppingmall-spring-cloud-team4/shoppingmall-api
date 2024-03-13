package com.nhnacademy.shoppingmall.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String userId)
    {
        super(userId + "는 이미 존재하는 userId 입니다.");
    }
}
