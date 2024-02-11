package com.kart.order.helper;

import org.springframework.stereotype.Component;

@Component
public class ProductHelper {

    public Double finalPrice(Double price, String discountOrTax, Long applicableValue) {
        Double finalPrice = price;
        if (discountOrTax.equalsIgnoreCase("discount")) {
            finalPrice = (price * (double) (100 - applicableValue) / 100);
        } else if (discountOrTax.equalsIgnoreCase("tax")) {
            finalPrice = (price * (double) (100 + applicableValue) / 100);
        } else {
            throw new RuntimeException();
        }
        return finalPrice;
    }
}
