package com.kart.order.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Optional;

@Builder
public record ProductDTO (
        @NotNull(message = "Product Name must not be null") String name,

        String description,

        @NotNull(message = "Product price must not be null")Double price,

        @NotNull(message = "Product quantity must not be null")Long quantity,

        @Nullable Optional<OptionalDTO> optional
) {
    public boolean hasOptional() {
        if(optional.isEmpty())
            return false;
        else
            return true;
    }
}
