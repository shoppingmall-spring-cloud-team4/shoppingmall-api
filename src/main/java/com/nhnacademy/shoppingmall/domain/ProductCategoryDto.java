package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategoryDto {
    Integer productId;
    Integer categoryId;
}
