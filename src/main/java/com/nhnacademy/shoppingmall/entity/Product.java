package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;


    @Column(name = "model_number")
    @Length(max = 10)
    private String modelNumber; // 모델번호

    @Column(name = "model_name")
    @Length(max = 100)
    private String modelName; // 모델이름

    @Column(name = "product_image")
    private String productImage; // 상품 이미지

    @Column(name = "unit_cost")
    private Integer unitCost; //가격

    @Length(max = 300)
    private String description; // 상품 설명

    @Builder
    public Product(Integer productId, Category category, String modelNumber, String modelName, String productImage, Integer unitCost,
            String description) {
        this.productId = productId;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImage = productImage;
        this.unitCost = unitCost;
        this.description = description;
    }
}
