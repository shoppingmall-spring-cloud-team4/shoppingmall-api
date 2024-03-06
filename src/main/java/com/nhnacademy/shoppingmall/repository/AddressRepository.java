package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<AddressResponse> findAddressesByUser_UserId(String userId);
    Optional<AddressResponse> findAddressByAddressIdAndUser_UserId(Integer addressId, String userId);
}
