package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Integer orderId;
    private LocalDateTime orderDate;
    private LocalDateTime shipDate;
    private String userId;
    private String zipcode;
    private String addressDetail;
    private List<OrderedProductDto> orderedProducts;
    private Integer totalPayment;
}
