package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getProducts();
    Page<ProductDto> getAllProductsPage(Pageable pageable);
    Optional<ProductDto> getProductById(Integer productId);
    void createProduct(ProductRegisterDto productRegisterDto);
    void updateProduct(ProductRegisterDto productRegisterDto, Integer productId);
    void deleteProduct(Integer productId);
}
