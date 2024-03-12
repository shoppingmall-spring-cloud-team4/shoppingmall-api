package com.nhnacademy.shoppingmall.exception;

public class CategoryMoreThanThree extends RuntimeException{

    public CategoryMoreThanThree(Integer productId)
    {
        super(productId + "는 이미 category 3개를 갖고 있습니다.");
    }
}
