package com.kart.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DiscountOrTaxDTO(
        @NotNull(message = "Product Id can't be null") Long id,
        @NotNull(message = "Discount or Tax option can't be null") String discountOrTax,
        @NotNull(message = "The value of Discount or Tax can't be null") Long applicableValue
) {
}
