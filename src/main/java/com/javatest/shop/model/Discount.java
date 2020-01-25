package com.javatest.shop.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class Discount {

    private String discountedProductName;
    @NonNull
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;

    public abstract void applyDiscount(Map<Product, Integer> cart);
}
