package com.nhnacademy.shoppingmall.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import com.nhnacademy.shoppingmall.domain.ReviewUpdateDto;
import com.nhnacademy.shoppingmall.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    @Test
    void getReviewsByProductId() throws Exception {
        Integer productId = 1;
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<ReviewDto> expectedReviews = Arrays.asList(
                new ReviewDto(5, LocalDateTime.now(), "comment1", "userId1"),
                new ReviewDto(5, LocalDateTime.now(), "comment2", "userId1")
        );
        Page<ReviewDto> reviewDtoPage = new PageImpl<>(expectedReviews, pageable, expectedReviews.size());

        when(reviewService.getPagesByProductId(productId, pageable)).thenReturn(reviewDtoPage);

        mockMvc.perform(get("/api/product/" + productId + "/review?page=" + page + "&size=" + size))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedReviews)));

        verify(reviewService, times(1)).getPagesByProductId(productId, pageable);
    }

    @Test
    void getReviewsByUserId() throws Exception {
        Integer productId = 1;
        String userId = "userId1";
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<ReviewDto> expectedReviews = Arrays.asList(
                new ReviewDto(5, LocalDateTime.now(), "comment1", userId),
                new ReviewDto(5, LocalDateTime.now(), "comment2", userId)
        );
        Page<ReviewDto> reviewDtoPage = new PageImpl<>(expectedReviews, pageable, expectedReviews.size());

        when(reviewService.getPagesByUserId(userId, pageable)).thenReturn(reviewDtoPage);

        mockMvc.perform(get("/api/product/" + productId + "/review/my_review?page=" + page + "&size=" + size)
                        .header("X-USER-ID", userId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedReviews)));

        verify(reviewService, times(1)).getPagesByUserId(userId, pageable);
    }

    @Test
    void createReview() throws Exception {
        Integer productId = 1;
        ReviewRegisterDto reviewRegisterDto = new ReviewRegisterDto("user1", 5, "Great item!");

        mockMvc.perform(post("/api/product/" + productId + "/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewRegisterDto)))
                .andExpect(status().isCreated());

        verify(reviewService, times(1)).createReview(reviewRegisterDto, productId);
    }

    @Test
    void updateReview() throws Exception {
        Integer productId = 1;
        Integer reviewId = 1;
        ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto(5, "Updated item comment!");

        mockMvc.perform(put("/api/product/" + productId + "/review/" + reviewId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewUpdateDto)))
                .andExpect(status().isOk());

        verify(reviewService, times(1)).updateReview(reviewUpdateDto, reviewId);
    }

    @Test
    void deleteReview() throws Exception {
        int productId = 1;
        int reviewId = 1;

        mockMvc.perform(delete("/api/product/" + productId + "/review/" + reviewId))
                .andExpect(status().isOk());

        Mockito.verify(reviewService, Mockito.times(1)).deleteReview(reviewId);
    }
}