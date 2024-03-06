package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.exception.AddressNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<AddressResponse> getAllAddresses(String userId);

    AddressResponse getAddress(Integer addressId, String userId) throws AddressNotFoundException;

    void createAddress(String userId, AddressRequest addressRequest);

    void updateAddress(Integer addressId, String userId,AddressRequest addressRequest) throws AddressNotFoundException;

    void deleteAddress(Integer addressId) throws AddressNotFoundException;
}
