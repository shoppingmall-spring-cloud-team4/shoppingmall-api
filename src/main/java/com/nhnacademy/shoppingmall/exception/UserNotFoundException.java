package com.nhnacademy.shoppingmall.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId)
    {
        super(userId + "는 존재하지 않습니다.");
    }
}
