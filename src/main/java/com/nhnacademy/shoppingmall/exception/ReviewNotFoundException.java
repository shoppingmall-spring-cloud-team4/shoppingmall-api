package com.nhnacademy.shoppingmall.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(Integer reviewId)
    {
        super(reviewId + "는 존재하지 않는 리뷰입니다.");
    }
}
