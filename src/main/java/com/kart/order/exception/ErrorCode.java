package com.kart.order.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    GENERIC_ERROR("Unable to process your request"),
    PRODUCT_ID_NOT_FOUND("Unable to find the specified product id");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
