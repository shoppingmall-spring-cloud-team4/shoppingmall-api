package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.service.PointService;
import com.nhnacademy.shoppingmall.service.ReviewService;
import com.nhnacademy.shoppingmall.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private PointService pointService;



    @Test
    void getAllUsers() throws Exception {
        // Given
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<UserDto> expectedUsers = new ArrayList<>(Arrays.asList(new UserDto("User 1", "password"), new UserDto( "User 2", "password2")));
        Page<UserDto> userDtoPage = new PageImpl<>(expectedUsers, pageable, expectedUsers.size());

        when(userService.getPagingUsers(pageable)).thenReturn(userDtoPage);

        mockMvc.perform(get("/api/user?page=" + page + "&size=" + size))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedUsers)));

        verify(userService, times(1)).getPagingUsers(pageable);
    }

    @Test
    void getUser() throws Exception {
        String userId = "id1";
        Optional<UserDto> expectedUser = Optional.of(new UserDto(userId, "password"));

        when(userService.getUserById(userId)).thenReturn(expectedUser);

        mockMvc.perform(get("/api/user/myPage")
                        .header("X-USER-ID", userId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedUser)));

        verify(userService, times(1)).getUserById(userId);
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
    void updateUser() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto("id1", "User 1", "password", "19991102");

        doNothing().when(userService).updateUser(any(UserRegisterDto.class), eq("id1"));

        mockMvc.perform(put("/api/user")
                        .header("X-USER-ID", "id1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(any(UserRegisterDto.class), eq("id1"));
    }

    @Test
    void deleteUserTest() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto("id1", "User 1", "password", "19991102");
        userService.createUser(userRegisterDto);

        String targetUserId = "id1";
        doNothing().when(userService).deleteUser(anyString());
        mockMvc.perform(delete("/api/user")
                        .header("X-USER-ID", targetUserId))
                .andExpect(status().isOk());
        verify(userService, times(1)).deleteUser(targetUserId);
    }
}