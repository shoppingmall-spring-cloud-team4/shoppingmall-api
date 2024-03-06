package com.nhnacademy.shoppingmall.domain;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String userId;
    private Integer addressId;
    private List<OrderedProductDto> orderProducts;
}
