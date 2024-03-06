package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderedProductDto {
    private Integer productId;
    private Integer quantity;
    private Long unitCost;
}
