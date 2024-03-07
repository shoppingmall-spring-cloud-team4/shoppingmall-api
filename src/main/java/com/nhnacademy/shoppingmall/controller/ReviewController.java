package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
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

    @GetMapping()
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok().body(reviewService.getReviewsByProductId(productId));
    }



    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody ReviewRegisterDto reviewRegisterDto) {
        reviewService.createReview(reviewRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@RequestBody ReviewRegisterDto reviewRegisterDto, @PathVariable Integer reviewId) {
        reviewService.updateReview(reviewRegisterDto, reviewId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
