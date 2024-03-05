package com.nhnacademy.shoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ShoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "cart_id")
    @Length(max = 120)
    private String cartId;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
