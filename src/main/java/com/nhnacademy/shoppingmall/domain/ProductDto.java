package com.nhnacademy.shoppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Integer productId;
    private String productImage;
    private String description;
    private String modelName;
    private Integer unitCost;
}
