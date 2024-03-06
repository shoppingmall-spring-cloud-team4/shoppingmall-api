package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getProducts();
    List<ProductDto> getProductsByCategoryId(Integer categoryId);
    Optional<ProductDto> getProductById(Integer productId);
    void createProduct(ProductRegisterDto productRegisterDto);
    void updateProduct(ProductRegisterDto productRegisterDto, Integer productId);
    void deleteProduct(Integer productId);
}
