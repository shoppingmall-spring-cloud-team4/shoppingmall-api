package com.nhnacademy.shoppingmall.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
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
        // Given
        Integer productId = 1;
        List<ReviewDto> reviewList = Arrays.asList(
                new ReviewDto(5, LocalDateTime.now(), "Great item!", "user1"),
                new ReviewDto(4, LocalDateTime.now(), "Nice item!", "user2")
        );
        given(reviewService.getReviewsByProductId(productId)).willReturn(reviewList);

        // When-Then
        mockMvc.perform(get("/api/product/" + productId + "/review")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating", is(reviewList.get(0).getRating())))
                .andExpect(jsonPath("$[0].comment", is(reviewList.get(0).getComment())))
                .andExpect(jsonPath("$[0].userId", is(reviewList.get(0).getUserId())))
                .andExpect(jsonPath("$[1].rating", is(reviewList.get(1).getRating())))
                .andExpect(jsonPath("$[1].comment", is(reviewList.get(1).getComment())))
                .andExpect(jsonPath("$[1].userId", is(reviewList.get(1).getUserId())));

        verify(reviewService, times(1)).getReviewsByProductId(productId);
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
        // Given
        Integer productId = 1;
        Integer reviewId = 1;
        ReviewUpdateDto reviewUpdateDto = new ReviewUpdateDto(5, "Updated item comment!");

        // When-Then
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