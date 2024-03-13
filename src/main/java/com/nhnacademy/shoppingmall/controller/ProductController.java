package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProductsPage(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(productService.getAllProductsPage(pageable).getContent());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductDto>> getProduct(@PathVariable("productId") Integer productId, HttpServletResponse response)
    {
        Cookie cookie = new Cookie("recentProduct", productId.toString());
        cookie.setMaxAge(60 * 60 * 24 * 3);
        response.addCookie(cookie);

        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @RequestMapping("/recentlyViewed")
    public String recentlyViewedPage(@CookieValue("recentProduct") List<String> recentProduct, Model model) {
        List<ProductDto> productDtoList = new ArrayList<>();

        for(String productId : recentProduct)
        {
            ProductDto productById = productService.getProductById(Integer.parseInt(productId)).orElse(null);
            if(productById != null)
            {
                productDtoList.add(productById);
            }

        }
        model.addAttribute("recentProduct", productDtoList);
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