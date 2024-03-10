package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<ProductDto> getAllBy();
    Optional<ProductDto> getByProductId(Integer productId);
}
