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
@Table(name = "Addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    @Length(max = 50)
    private String zipcode;

    @Column(name = "address_detail")
    @Length(max = 50)
    private String addressDetail;

    @Column(name = "delivery_request")
    @Length(max = 100)
    private String deliveryRequest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
