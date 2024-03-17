package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.Point;
import com.nhnacademy.shoppingmall.service.PointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PointController.class)
class PointControllerUnitTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PointService pointService;

    @Test
    void getPoint() throws Exception {
        Point point = Point.builder()
                           .userId("aaa")
                           .point(6000)
                           .build();
        given(pointService.getPoint("aaa")).willReturn(point);

        mockMvc.perform(get("/api/shop/points")
                       .header("X-USER-ID", "aaa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.point", equalTo(6000)));
    }

    @Test
    void createPoint() throws Exception {
        mockMvc.perform(post("/api/shop/points/create")
                       .header("X-USER-ID", "aaa"))
               .andExpect(status().isCreated());
    }

    @Test
    void updatePoints() throws Exception {
        PointRegisterRequest pointRegisterRequest = new PointRegisterRequest(100, "Test Point Update");
        mockMvc.perform(post("/api/shop/points/update")
                       .header("X-USER-ID", "aaa")
                       .content(objectMapper.writeValueAsString(pointRegisterRequest))
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }
}