package com.nhnacademy.shoppingmall.domain;

import lombok.Data;

@Data
public class AddressRequest {
    private String zipcode;
    private String addressDetail;
    private String deliveryRequest;
}
