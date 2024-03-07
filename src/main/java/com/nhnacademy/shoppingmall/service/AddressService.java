package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getAllAddresses(String userId);

    AddressResponse getAddress(Integer addressId, String userId);

    void createAddress(String userId, AddressRequest addressRequest);

    void updateAddress(Integer addressId, String userId,AddressRequest addressRequest);

    void deleteAddress(Integer addressId);
}
