package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.service.ReviewService;
import com.nhnacademy.shoppingmall.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private ReviewService reviewService;


    @Test
    void getAllUsers() throws Exception {
        List<UserDto> mockUserList = Arrays.asList(new UserDto("id1","User 1", 100), new UserDto("id2", "User 2", 200));
        Mockito.when(userService.getUsers()).thenReturn(mockUserList);

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", is("id1")))
                .andExpect(jsonPath("$[0].userName", is("User 1")))
                .andExpect(jsonPath("$[0].userPoint", is(100)))
                .andExpect(jsonPath("$[1].userId", is("id2")))
                .andExpect(jsonPath("$[1].userName", is("User 2")))
                .andExpect(jsonPath("$[1].userPoint", is(200)));

        verify(userService, times(1)).getUsers();
    }

    @Test
    void getUser() throws Exception{
        Optional<UserDto> mockUser = Optional.of(new UserDto("id1", "User 1", 100));
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(mockUser);

        mockMvc.perform(get("/api/user/{userId}", "id1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("id1")))
                .andExpect(jsonPath("$.userName", is("User 1")))
                .andExpect(jsonPath("$.userPoint", is(100)));

        verify(userService, times(1)).getUserById(Mockito.anyString());
    }

    @Test
    void getReviewsByUserId() throws Exception{
        List<ReviewDto> mockReviewList = Arrays.asList
                (new ReviewDto(5, LocalDateTime.now(), "comment", "userId"),
                        new ReviewDto(4, LocalDateTime.now(), "comment", "userId"));
        Mockito.when(reviewService.getReviewsByUserId(Mockito.anyString())).thenReturn(mockReviewList);

        mockMvc.perform(get("/api/user/{userId}/review", "id1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is(notNullValue())))
                .andExpect(jsonPath("$[1]", is(notNullValue())));

        verify(reviewService, times(1)).getReviewsByUserId(Mockito.anyString());
    }

    @Test
    void createUser() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto("id1", "User 1", "password", "19991102");

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).createUser(Mockito.any(UserRegisterDto.class));
    }

    @Test
    void updateUser() throws Exception{
        UserRegisterDto userRegisterDto = new UserRegisterDto("id1", "User 1", "password", "19991102");

        mockMvc.perform(put("/api/user/{userId}", "id1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(Mockito.any(UserRegisterDto.class), Mockito.anyString());
    }

    @Test
    void deleteUser() throws Exception{
        mockMvc.perform(delete("/api/user/{userId}", "id1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(Mockito.anyString());
    }
}