package com.nhnacademy.shoppingmall.domain;

import lombok.Data;

@Data
public class AddressResponse {
    private Integer addressId;
    private String zipcode;
    private String addressDetail;
    private String deliveryRequest;
}
