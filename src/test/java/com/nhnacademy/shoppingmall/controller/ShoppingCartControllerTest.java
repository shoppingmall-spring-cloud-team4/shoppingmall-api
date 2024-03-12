package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartRegisterDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartUpdateDto;
import com.nhnacademy.shoppingmall.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    void getAllShoppingList() throws Exception {
        String userId = "testUser";
        List<ShoppingCartDto> shoppingCartDtos = Arrays.asList(
                new ShoppingCartDto(userId, 1, 5),
                new ShoppingCartDto(userId, 2, 3)
        );
        when(shoppingCartService.getAllShoppingList(userId)).thenReturn(shoppingCartDtos);

        mockMvc.perform(get("/api/user/" + userId + "/shoppingCart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is(userId)))
                .andExpect(jsonPath("$[1].userId", is(userId)));

        verify(shoppingCartService, times(1)).getAllShoppingList(userId);
    }

    @Test
    void createShoppingCart() throws Exception {
        ShoppingCartRegisterDto shoppingCartRegisterDto = new ShoppingCartRegisterDto("Asdf",1,1);

        mockMvc.perform(post("/api/user/" + shoppingCartRegisterDto.getUserId() + "/shoppingCart")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(shoppingCartRegisterDto)))
                .andExpect(status().isCreated());

        verify(shoppingCartService, times(1)).createShoppingCart(any(ShoppingCartRegisterDto.class));
    }

    @Test
    void updateShoppingCart() throws Exception {
        String userId = "testUser";
        Integer recordId = 1;
        ShoppingCartUpdateDto updateDto = new ShoppingCartUpdateDto();
        updateDto.setQuantity(3);

        doNothing().when(shoppingCartService).updateShppoingCart(updateDto, recordId);

        mockMvc.perform(put("/api/user/" + userId + "/shoppingCart/" + recordId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk());

        verify(shoppingCartService, times(1)).updateShppoingCart(updateDto, recordId);
    }

    @Test
    void deleteShoppingCart() throws Exception {
        Integer recordId = 1;
        String userId = "testUser";

        doNothing().when(shoppingCartService).deleteShppoingCart(recordId);

        mockMvc.perform(delete("/api/user/" + userId + "/shoppingCart/" + recordId))
                .andExpect(status().isOk());

        verify(shoppingCartService, times(1)).deleteShppoingCart(recordId);
    }

}