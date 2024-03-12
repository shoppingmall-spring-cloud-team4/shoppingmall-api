package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductDto>> getProduct(@PathVariable("productId") Integer productId)
    {
        return ResponseEntity.ok().body(productService.getProductById(productId));
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
