package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<AddressResponse> getAllAddresses();

    Optional<AddressResponse> getAddress(Integer addressId);

    AddressResponse createAddress(AddressRequest addressRequest);

    AddressResponse updateAddress(AddressRequest addressRequest);

    void deleteAddress(Integer addressId);
}
