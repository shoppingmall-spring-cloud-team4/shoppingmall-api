package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OrderDetails")
public class OrderDetail {

    @EmbeddedId
    private Pk pk;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity; // 수량

    @Column(name = "unit_cost")
    private Integer unitCost; //가격

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {
        private Integer orderId; // 주문번호
        private Integer productId; // 상품번호
    }

    @Builder
    public OrderDetail(Order order, Product product, Integer quantity, Integer unitCost, Pk pk) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.pk = pk;
    }

}
