package com.javatest.shop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PercentageDiscountTest {

    @Test
    void applyDiscount_WhenBuying3Apples_ShouldApply10PercentageOnPrice() {
        Map<Product, ProductPrice> cart =   new HashMap<>();
        Product product = Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        ProductPrice productPrice = new ProductPrice(3, null);
        cart.put(product,productPrice);
        Discount discount   =   PercentageDiscount
                .builder()
                .discountedProductName("Apple")
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(6))
                .discountPercentage(BigDecimal.valueOf(10))
                .build();
        discount.applyDiscount(cart);
        assertTrue(BigDecimal.valueOf(0.27).equals(cart.get(product).getTotalPrice()));
    }

}