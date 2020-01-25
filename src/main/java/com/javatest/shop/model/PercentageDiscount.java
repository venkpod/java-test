package com.javatest.shop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Getter
public class PercentageDiscount extends Discount {

    @NonNull
    private BigDecimal discountPercentage;

    @Builder
    public PercentageDiscount(String discountedProductName,
                              LocalDate startDate,
                              LocalDate endDate,
                              BigDecimal discountPercentage) {
        super(discountedProductName, startDate, endDate);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void applyDiscount(Map<Product, Integer> cart) { }
}
