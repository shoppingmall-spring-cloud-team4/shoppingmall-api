package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import com.nhnacademy.shoppingmall.domain.ReviewUpdateDto;
import com.nhnacademy.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/{productId}/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok().body(reviewService.getReviewsByProductId(productId));
    }


    // userId는 Gateway에서 session으로 받아서 처리하면 좋을듯
    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody ReviewRegisterDto reviewRegisterDto, @PathVariable("productId") Integer productId) {
        reviewService.createReview(reviewRegisterDto, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewUpdateDto reviewUpdateDto, @PathVariable Integer reviewId) {
        reviewService.updateReview(reviewUpdateDto, reviewId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
