package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getProducts();
    Page<ProductDto> getAllProductsPage(Pageable pageable);
    Optional<ProductDto> getProductById(Integer productId);
    void createProduct(ProductRegisterDto productRegisterDto);
    void updateProduct(ProductRegisterDto productRegisterDto, Integer productId);
    void deleteProduct(Integer productId);
    void saveRecentProductToCookie(Integer productId, HttpServletResponse response, HttpServletRequest request) throws IOException;
    List<ProductDto> getRecentProductsFromCookie(HttpServletRequest request) throws IOException;
}
