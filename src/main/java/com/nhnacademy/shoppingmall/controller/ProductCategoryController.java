package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ProductCategoryDto;
import com.nhnacademy.shoppingmall.domain.ProductCategoryRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/category/{productId}")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getProductCategoriesByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok().body(productCategoryService.getProductCategoriesByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<Void> createProductCategory(@RequestBody ProductCategoryRegisterDto productCategoryRegisterDto, @PathVariable Integer productId)
    {
        productCategoryService.createProductCategory(productId, productCategoryRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Integer productId, @PathVariable Integer categoryId) {
        productCategoryService.deleteProductCategory(productId, categoryId);
        return ResponseEntity.noContent().build();
    }
}
