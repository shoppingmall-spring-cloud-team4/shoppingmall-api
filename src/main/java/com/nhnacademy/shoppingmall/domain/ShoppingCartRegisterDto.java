package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ShoppingCartRegisterDto {
    private String userId;
    private LocalDateTime dateCreated;
    private Integer quantity;
}
