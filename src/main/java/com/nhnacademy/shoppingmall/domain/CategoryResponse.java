package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private Integer categoryId;
    private String categoryName;
}
