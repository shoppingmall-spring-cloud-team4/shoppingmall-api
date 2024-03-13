package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.AddressRequest;
import com.nhnacademy.shoppingmall.domain.AddressResponse;
import com.nhnacademy.shoppingmall.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity
                .ok()
                .body(addressService.getAllAddresses(userId));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddress(@RequestHeader("X-USER-ID") String userId, @PathVariable("addressId") Integer addressId){
        return ResponseEntity
                .ok()
                .body(addressService.getAddress(addressId, userId));
    }

    @PostMapping
    public ResponseEntity<Void> createAddress(@RequestHeader("X-USER-ID") String userId, @RequestBody AddressRequest addressRequest){
        addressService.createAddress(userId, addressRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Void> updateAddress(@RequestHeader("X-USER-ID") String userId, @PathVariable("addressId") Integer addressId, @RequestBody AddressRequest addressRequest){
        addressService.updateAddress(addressId, userId, addressRequest);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("addressId") Integer addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity
                .ok()
                .build();
    }

}
