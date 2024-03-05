package com.nhnacademy.shoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "model_number")
    @Length(max = 10)
    private String modelNumber;

    @Column(name = "model_name")
    @Length(max = 100)
    private String modelName;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "unit_cost")
    @Length(max = 15)
    private long unitCost;

    @Length(max = 300)
    private String description;
}
