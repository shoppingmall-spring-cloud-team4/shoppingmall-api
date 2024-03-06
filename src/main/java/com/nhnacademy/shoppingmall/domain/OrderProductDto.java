package com.nhnacademy.shoppingmall.domain;

import lombok.Data;

@Data
public class OrderProductDto {
    private Integer productId;
    private Integer quantity;
}
