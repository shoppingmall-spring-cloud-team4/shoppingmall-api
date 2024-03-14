package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsPage(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(productService.getAllProductsPage(pageable).getContent());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductDto>> getProduct(@PathVariable("productId") Integer productId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        productService.saveRecentProductToCookie(productId, response, request);
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping("/recentProducts")
    public ResponseEntity<List<ProductDto>> recentlyViewedPage(HttpServletRequest request) throws IOException {
        List<ProductDto> productDtoList = productService.getRecentProductsFromCookie(request);
        return ResponseEntity.ok().body(productDtoList);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRegisterDto productRegisterDto) {
        productService.createProduct(productRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductRegisterDto productRegisterDto, @PathVariable("productId") Integer productId) {
        productService.updateProduct(productRegisterDto, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}