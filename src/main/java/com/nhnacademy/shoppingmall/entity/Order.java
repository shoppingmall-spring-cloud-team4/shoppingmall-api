package com.nhnacademy.shoppingmall.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate; //주문 날짜

    @Column(name = "ship_date")
    private LocalDateTime shipDate; //배송 날짜

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //유저 아이디

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address; //주소 아이디

    @Builder
    public Order(LocalDateTime orderDate, LocalDateTime shipDate, User user, Address address) {
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.user = user;
        this.address = address;
    }
}
