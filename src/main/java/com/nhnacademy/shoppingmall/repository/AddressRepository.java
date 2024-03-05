package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
