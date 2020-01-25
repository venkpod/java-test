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
    public void applyDiscount(Map<Product, ProductPrice> cart) {
        //get the Product
        Optional<Map.Entry<Product, ProductPrice>> optionalProductProductPriceEntry = cart.entrySet()
                .stream()
                .filter(productProductPriceEntry ->
                        productProductPriceEntry
                                .getKey()
                                .getProductName()
                                .equalsIgnoreCase(this.getDiscountedProductName()))
                .findFirst();

        optionalProductProductPriceEntry.ifPresent(productProductPriceEntry -> {
            Product discountProduct = productProductPriceEntry.getKey();
            ProductPrice discountProductPrice = productProductPriceEntry.getValue();
            BigDecimal totalPrice = discountProduct.getProductUnitPrice()
                    .multiply(BigDecimal.valueOf(discountProductPrice.getQuantity()));
            BigDecimal disCountedPrice = totalPrice
                    .multiply(this.discountPercentage)
                    .divide(BigDecimal.valueOf(100));

            //set the price after discount

            discountProductPrice.setTotalPrice(totalPrice.subtract(disCountedPrice));
        });
    }
}
