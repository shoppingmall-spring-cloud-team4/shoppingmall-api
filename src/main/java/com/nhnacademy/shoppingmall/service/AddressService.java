package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.exception.AddressNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    List<AddressResponse> getAllAddresses(String userId);

    AddressResponse getAddress(Integer addressId, String userId);

    void createAddress(String userId, AddressRequest addressRequest);

    void updateAddress(Integer addressId, String userId,AddressRequest addressRequest);

    void deleteAddress(Integer addressId);
}
