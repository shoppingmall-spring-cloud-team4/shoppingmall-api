package com.nhnacademy.shoppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartDto {
    private Integer productId;
    private int quantity;
}
