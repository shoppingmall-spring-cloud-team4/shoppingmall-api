package com.nhnacademy.shoppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDto {
    private int rating;
    private LocalDateTime reviewDateCreated;
    private String comment;
    private Integer userId;
}
