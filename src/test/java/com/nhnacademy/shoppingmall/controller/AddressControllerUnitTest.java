package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.service.AddressService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
public class AddressControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    @Test
    void testGetAllAddresses() throws Exception {
        AddressResponse addressResponse = new AddressResponse(100, "123대로", "a빌딩", "집 앞");
        List<AddressResponse> addressResponses = Collections.singletonList(addressResponse);

        given(addressService.getAllAddresses("aaa"))
                .willReturn(addressResponses);

        MvcResult result = mockMvc.perform(get("/api/shop/address")
                        .header("X-USER-ID", "aaa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].zipcode", equalTo("123대로")))
                .andReturn();

        String contentType = result.getResponse().getContentType();
        Assertions.assertThat(contentType).startsWith(MediaType.APPLICATION_JSON.toString());
    }

    @Test
    void getAddress() throws Exception {
        AddressResponse addressResponse = new AddressResponse(100, "123대로", "a빌딩", "집 앞");

        given(addressService.getAddress(100, "aaa"))
                .willReturn(addressResponse);

        mockMvc.perform(get("/api/shop/address/{addressId}", 100)
                        .header("X-USER-ID", "aaa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressDetail", equalTo(addressResponse.getAddressDetail())));
    }

    @Test
    void createAddress() throws Exception {
        AddressRequest addressRequest = new AddressRequest("456대로", "b빌딩", "딩동");

        mockMvc.perform(post("/api/shop/address")
                        .header("X-USER-ID", "aaa")
                        .content(objectMapper.writeValueAsString(addressRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAddress() throws Exception {
        AddressRequest addressRequest = new AddressRequest("789대로", "c빌딩", "집 뒤");

        mockMvc.perform(put("/api/shop/address/{addressId}", 100)
                        .header("X-USER-ID", "aaa")
                        .content(objectMapper.writeValueAsString(addressRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAddress() throws Exception {
        mockMvc.perform(delete("/api/shop/address/{addressId}", 100))
                .andExpect(status().isOk());
    }
}
