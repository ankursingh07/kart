package com.kart.order.helper;

import com.kart.order.dto.DiscountOrTaxDTO;
import com.kart.order.dto.OptionalDTO;
import com.kart.order.dto.ProductDTO;
import com.kart.order.persistence.entity.ProductEntity;

import java.util.Optional;

public class ProductHelperTest {

    public static ProductDTO getProductDTO() {
        return ProductDTO.builder()
                .name("test-product")
                .description("test-description")
                .price(7.77)
                .quantity(7L)
                .optional(
                        Optional.ofNullable(OptionalDTO.builder()
                                .discountOrTax("discount")
                                .applicableValue(1L)
                                .build()
                        )
                )
                .build();
    }

    public static ProductEntity getProductEntity() {
        return ProductEntity.builder()
                .id(1L)
                .name("test-name")
                .description("test-description")
                .price(7.77)
                .quantity(7L)
                .build();
    }

    public static DiscountOrTaxDTO getDiscountOrTaxDTO() {
        return DiscountOrTaxDTO.builder()
                .id(1L)
                .discountOrTax("discount")
                .applicableValue(1L)
                .build();
    }

}
