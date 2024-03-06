package com.nhnacademy.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ProductCategory")
public class ProductCategory {

    @EmbeddedId
    private Pk pk;

    @MapsId("categoryId")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {
        private Integer categoryId; // 카테고리 아이디
        private Integer productId; // 상품번호
    }

    @Builder
    public ProductCategory(Category category, Product product) {
        this.category = category;
        this.product = product;
    }
}
