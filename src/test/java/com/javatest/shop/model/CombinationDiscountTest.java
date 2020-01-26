package com.javatest.shop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationDiscountTest {

    @Test
    void applyDiscount_WhenBuying2Tins_shouldGetDiscountonLoaf() {
        Map<Product, ProductPrice> cart = new HashMap<>();
        Product soupProduct =   Product
                .builder()
                .productName("Soup")
                .productUnit(Unit.SOUP)
                .productUnitPrice(BigDecimal.valueOf(.65))
                .build();
        ProductPrice soupProductPrice = new ProductPrice(2, null);
        Product breadProduct =   Product
                .builder()
                .productName("Bread")
                .productUnit(Unit.BREAD)
                .productUnitPrice(BigDecimal.valueOf(.80))
                .build();
        ProductPrice breadProductPrice = new ProductPrice(1, null);
        cart.put(soupProduct, soupProductPrice);
        cart.put(breadProduct, breadProductPrice);
        Discount soutDiscount   =   CombinationDiscount
                .builder()
                .discountedProductName("Soup")
                .discountOnProductName("Bread")
                .discountPricePercentage((BigDecimal.valueOf(50)))
                .qualifyingProdMinQnty(2)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(6))
                .build();
        soutDiscount.applyDiscount(cart);
        assertTrue(BigDecimal.valueOf(0.40).equals(cart.get(breadProduct).getTotalPrice()));
    }
}