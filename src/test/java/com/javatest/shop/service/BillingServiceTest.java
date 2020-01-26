package com.javatest.shop.service;

import com.javatest.shop.model.Product;
import com.javatest.shop.model.Unit;
import com.javatest.shop.repos.InMemoryDiscountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShoppingCartService.class, BillingService.class, InMemoryDiscountRepo.class})
class BillingServiceTest {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private BillingService billingService;

    @BeforeEach
    public void setup() {
        shoppingCartService.getCart().clear();
    }


    @Test
    public void getTotalPrice_WhenAdded6ApplesAndBottleMilk_Purchasedin5DaysTime_PercentageDiscountisApplied_ShouldReturnDiscountedPrice() {
        Product apple = Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        Product milk = Product
                .builder()
                .productName("Milk")
                .productUnit(Unit.MILK)
                .productUnitPrice(BigDecimal.valueOf(1.30))
                .build();
        shoppingCartService.addProductToCart(apple, 6);
        shoppingCartService.addProductToCart(milk, 1);
        BigDecimal totalPrice = billingService.getTotalPrice(shoppingCartService.getCart(), LocalDate.now().plusDays(5));
        assertTrue(BigDecimal.valueOf(1.84).equals(totalPrice));
    }

    @Test
    public void getTotalPrice_WhenAdded6ApplesAndBottleMilk_PurchasedToday_PercentageDiscountisNotApplied_ShouldReturnTotalPrice() {
        Product apple = Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        Product milk = Product
                .builder()
                .productName("Milk")
                .productUnit(Unit.MILK)
                .productUnitPrice(BigDecimal.valueOf(1.30))
                .build();
        shoppingCartService.addProductToCart(apple, 6);
        shoppingCartService.addProductToCart(milk, 1);
        BigDecimal totalPrice = billingService.getTotalPrice(shoppingCartService.getCart(), LocalDate.now());
        assertTrue(BigDecimal.valueOf(1.90).equals(totalPrice));
    }

    @Test
    public void getTotalPrice_WhenAdded3TinsAnd2Loafs_PurchasedToday_CombinationDiscountisApplied_ShouldReturnDiscountedPrice() {
        Product soup = Product
                .builder()
                .productName("Soup")
                .productUnit(Unit.SOUP)
                .productUnitPrice(BigDecimal.valueOf(.65))
                .build();
        Product loafs = Product
                .builder()
                .productName("Bread")
                .productUnit(Unit.BREAD)
                .productUnitPrice(BigDecimal.valueOf(.80))
                .build();
        shoppingCartService.addProductToCart(soup, 3);
        shoppingCartService.addProductToCart(loafs, 2);
        BigDecimal totalPrice = billingService.getTotalPrice(shoppingCartService.getCart(), LocalDate.now());
        assertTrue(BigDecimal.valueOf(3.15).equals(totalPrice));
    }

    @Test
    public void getTotalPrice_WhenAdded3TinsAnd2Loafs_PurchasedAfter7Days_CombinationDiscountisNotApplied_ShouldReturnTotalPrice() {
        Product soup = Product
                .builder()
                .productName("Soup")
                .productUnit(Unit.SOUP)
                .productUnitPrice(BigDecimal.valueOf(.65))
                .build();
        Product loafs = Product
                .builder()
                .productName("Bread")
                .productUnit(Unit.BREAD)
                .productUnitPrice(BigDecimal.valueOf(.80))
                .build();
        shoppingCartService.addProductToCart(soup, 3);
        shoppingCartService.addProductToCart(loafs, 2);
        BigDecimal totalPrice = billingService.getTotalPrice(shoppingCartService.getCart(), LocalDate.now().plusDays(7));
        assertTrue(BigDecimal.valueOf(3.55).equals(totalPrice));
    }

    @Test
    public void getTotalPrice_WhenAdded4TinsAnd1Loafs_PurchasedToday_CombinationDiscountisApplied_ShouldReturnDiscountedPrice() {
        Product soup = Product
                .builder()
                .productName("Soup")
                .productUnit(Unit.SOUP)
                .productUnitPrice(BigDecimal.valueOf(.65))
                .build();
        Product loafs = Product
                .builder()
                .productName("Bread")
                .productUnit(Unit.BREAD)
                .productUnitPrice(BigDecimal.valueOf(.80))
                .build();
        shoppingCartService.addProductToCart(soup, 4);
        shoppingCartService.addProductToCart(loafs, 1);
        BigDecimal totalPrice = billingService.getTotalPrice(shoppingCartService.getCart(), LocalDate.now());
        assertTrue(BigDecimal.valueOf(3.00).setScale(2).equals(totalPrice));
    }
}