package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; //카테고리 아이디

    @Column(name = "model_number")
    @Length(max = 10)
    private String modelNumber; //모델번호

    @Column(name = "model_name")
    @Length(max = 100)
    private String modelName; //모델이름

    @Column(name = "product_image")
    private String productImage; //상품 이미지

    @Column(name = "unit_cost")
    @Length(max = 15)
    private Long unitCost; //가격

    @Length(max = 300)
    private String description; //상품 설명

    @Builder
    public Product(Category category, String modelNumber, String modelName, String productImage, long unitCost, String description) {
        this.category = category;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.productImage = productImage;
        this.unitCost = unitCost;
        this.description = description;
    }
}
