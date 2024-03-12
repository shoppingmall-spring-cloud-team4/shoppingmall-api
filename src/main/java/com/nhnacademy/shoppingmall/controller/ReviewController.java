package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import com.nhnacademy.shoppingmall.domain.ReviewUpdateDto;
import com.nhnacademy.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/{productId}/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Integer productId, @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(reviewService.getPagesByProductId(productId, pageable).getContent());
    }


    // User가 작성한 reviews
    @GetMapping("/my_review")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@RequestHeader("X-USER-ID") String userId, @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(reviewService.getPagesByUserId(userId, pageable).getContent());
    }
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
