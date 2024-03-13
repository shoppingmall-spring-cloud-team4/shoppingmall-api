package com.nhnacademy.shoppingmall.entity;

import lombok.*;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Table(name = "Addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Length(max = 50)
    private String zipcode; //우편번호

    @Column(name = "address_detail")
    @Length(max = 50)
    private String addressDetail; //상세주소

    @Column(name = "delivery_request")
    @Length(max = 100)
    private String deliveryRequest; //배송시 요청사항

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Address(Integer addressId, String zipcode, String addressDetail, String deliveryRequest, User user) {
        this.addressId = addressId;
        this.zipcode = zipcode;
        this.addressDetail = addressDetail;
        this.deliveryRequest = deliveryRequest;
        this.user = user;
    }
}
