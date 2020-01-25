package com.javatest.shop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Getter
public class CombinationDiscount extends Discount {

    @NonNull
    private Integer qualifyingProdMinQnty;
    @NonNull
    private String discountOnProductName;
    @NonNull
    private BigDecimal discountPricePercentage;

    @Builder
    public CombinationDiscount(String discountedProductName,
                               LocalDate startDate,
                               LocalDate endDate,
                               Integer qualifyingProdMinQnty,
                               String discountOnProductName,
                               BigDecimal discountPricePercentage) {
        super(discountedProductName, startDate, endDate);
        this.discountOnProductName = discountOnProductName;
        this.qualifyingProdMinQnty = qualifyingProdMinQnty;
        this.discountPricePercentage = discountPricePercentage;
    }

    @Override
    public void applyDiscount(Map<Product, Integer> cart) { }
}
