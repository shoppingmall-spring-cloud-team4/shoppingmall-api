package com.nhnacademy.shoppingmall.exception;

public class AlreadyExistReviewException extends RuntimeException{
    public AlreadyExistReviewException(String userId)
    {
        super(userId + "께서는 이미 해당 상품에 대한 리뷰를 작성하셨습니다.");
    }
}
