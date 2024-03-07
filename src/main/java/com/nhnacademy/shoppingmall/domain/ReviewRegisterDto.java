package com.nhnacademy.shoppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewRegisterDto {
    private Integer rating;
    private String comment;
    private String userId;
    private Integer productId;
}
