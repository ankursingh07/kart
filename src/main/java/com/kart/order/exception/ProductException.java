package com.kart.order.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public ProductException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
