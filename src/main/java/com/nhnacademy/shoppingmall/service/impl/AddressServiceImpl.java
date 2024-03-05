package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.entity.Address;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.repository.AddressRepository;
import com.nhnacademy.shoppingmall.service.AddressService;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<AddressResponse> getAllAddresses(String userId) {
        return addressRepository.findAddressesByUser_UserId(userId);
    }

    @Override
    public AddressResponse getAddress(Integer addressId, String userId) {
        return addressRepository.findAddressByAddressIdAndUser_UserId(addressId, userId).orElse(null);
    }

    @Override
    public void createAddress(String userId, AddressRequest addressRequest) {
        Address address = Address.builder()
                .user(User.builder()
                        .userId(userId)
                        .build())
                .build();
        BeanUtils.copyProperties(addressRequest, address);

        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Integer addressId, String userId, AddressRequest addressRequest) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            BeanUtils.copyProperties(addressRequest, address);

            addressRepository.save(address);
        } else {
            throw new IllegalArgumentException("주소를 업데이트할 수 없습니다.");
        }
    }

    @Override
    public void deleteAddress(Integer addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            addressRepository.delete(address);
        } else {
            throw new IllegalArgumentException("주소를 삭제할 수 없습니다.");
        }
    }
}
