package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.entity.Address;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.exception.AddressNotFoundException;
import com.nhnacademy.shoppingmall.repository.AddressRepository;
import com.nhnacademy.shoppingmall.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public List<AddressResponse> getAllAddresses(String userId) {
        return addressRepository.findAddressesByUser_UserId(userId);
    }

    @Override
    public AddressResponse getAddress(Integer addressId, String userId){
        return addressRepository.findAddressByAddressIdAndUser_UserId(addressId, userId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
    }

    @Override
    public void createAddress(String userId, AddressRequest addressRequest) {
        Address address = Address.builder()
                .user(User.builder()
                        .userId(userId)
                        .build())
                .zipcode(addressRequest.getZipcode())
                .addressDetail(addressRequest.getAddressDetail())
                .deliveryRequest(addressRequest.getDeliveryRequest())
                .build();

        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Integer addressId, String userId, AddressRequest addressRequest) throws AddressNotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            Address updatedAddress = Address.builder()
                    .addressId(address.getAddressId())
                    .zipcode(addressRequest.getZipcode())
                    .addressDetail(addressRequest.getAddressDetail())
                    .deliveryRequest(addressRequest.getDeliveryRequest())
                    .user(address.getUser())
                    .build();

            addressRepository.save(updatedAddress);
        } else {
            throw new AddressNotFoundException(addressId);
        }
    }

    @Override
    public void deleteAddress(Integer addressId) throws AddressNotFoundException {
        if (addressRepository.findById(addressId).isPresent()) {
            addressRepository.deleteById(addressId);
        } else {
            throw new AddressNotFoundException(addressId);
        }
    }
}
