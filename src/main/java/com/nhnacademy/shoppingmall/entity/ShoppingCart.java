package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "ShoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "cart_id")
    @Length(max = 120)
    private String cartId; //장바구니 아이디

    @Column(name = "date_created")
    private LocalDateTime dateCreated; //생성일

    private Integer quantity; //수량

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; //상품번호

    public ShoppingCart(String cartId, LocalDateTime dateCreated, Integer quantity, Product product) {
        this.cartId = cartId;
        this.dateCreated = dateCreated;
        this.quantity = quantity;
        this.product = product;
    }
}
