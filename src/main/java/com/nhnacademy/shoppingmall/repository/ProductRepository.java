package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
