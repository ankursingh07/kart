package com.kart.order.dto;

import lombok.Builder;

@Builder
public record ErrorResponseDTO(
        String errorCode,
        String errorMessage,
        String clientErrorMessage
) {
}
