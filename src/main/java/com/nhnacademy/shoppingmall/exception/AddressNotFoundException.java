package com.nhnacademy.shoppingmall.exception;

public class AddressNotFoundException extends Exception {
    public AddressNotFoundException(Integer addressId) {
        super("address not found: id=" + addressId);
    }

}
