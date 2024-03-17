package com.nhnacademy.shoppingmall.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateUserIdException extends RuntimeException{
    public DuplicateUserIdException(String userId) {
        super("동일한 id가 이미 존재합니다." + userId);
    }
}