package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {
    private Integer addressId;
    private String zipcode;
    private String addressDetail;
    private String deliveryRequest;
}
