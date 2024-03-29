package com.nhnacademy.shoppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRegisterDto {
    private Integer productId;
    private String modelNumber;
    private String modelName;
    private String productImage;
    private Integer unitCost;
    private String description;
}
