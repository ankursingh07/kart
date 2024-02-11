package com.kart.order.web.exceptionhandler;

import com.kart.order.dto.ErrorResponseDTO;
import com.kart.order.exception.ProductException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String GENERIC_ERROR = "GENERIC_ERROR";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGenericError(Exception exception) {
        log.error("Unexpected exception has occurred", exception);

        return ErrorResponseDTO.builder()
                .errorCode(GENERIC_ERROR)
                .errorMessage(exception.getMessage())
                .build();
    }

    @ExceptionHandler(ProductException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleProductException(ProductException exception) {
        log.error("handling ProductException", exception);
        return ErrorResponseDTO.builder()
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getErrorMessage())
                .build();
    }
}
