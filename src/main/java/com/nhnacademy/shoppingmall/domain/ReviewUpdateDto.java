package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewUpdateDto {
    private Integer rating;
    private String comment;
}
