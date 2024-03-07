package com.nhnacademy.shoppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRegisterDto {
    private Integer productId;
    private Integer categoryId;
    private String modelNumber;
    private String modelName;
    private String productImage;
    private Long unitCost;
    private String description;
}
