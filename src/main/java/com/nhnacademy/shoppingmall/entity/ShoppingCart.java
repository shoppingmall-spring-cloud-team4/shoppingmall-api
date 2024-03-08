package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_created")
    private LocalDateTime dateCreated; // 생성일

    private Integer quantity; // 수량

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // 상품번호

    @Builder
    public ShoppingCart(Integer recordId, User user, LocalDateTime dateCreated, Integer quantity, Product product) {
        this.recordId = recordId;
        this.user = user;
        this.dateCreated = dateCreated;
        this.quantity = quantity;
        this.product = product;
    }
}
