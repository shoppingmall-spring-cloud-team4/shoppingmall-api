package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT new com.nhnacademy.shoppingmall.domain.ReviewDto(r.rating, r.reviewDateCreated, r.comment, r.user.userId) FROM Review r WHERE r.user.userId = :userId")
    List<ReviewDto> getAllByUser_UserId(@Param("userId") String userId);

    @Query("SELECT new com.nhnacademy.shoppingmall.domain.ReviewDto(r.rating, r.reviewDateCreated, r.comment, r.user.userId) FROM Review r WHERE r.product.productId = :productId")
    List<ReviewDto> getAllByProduct_ProductId(@Param("productId") Integer productId);
}