package com.javatest.shop.service;

import com.javatest.shop.model.Product;
import com.javatest.shop.model.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ShoppingCartService.class)
public class ShoppingCartServiceTest {

    @Autowired
    ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setup(){
        shoppingCartService.getCart().clear();
    }

    @Test
    public void addProductToCart_WhenAdding6Apples_shouldReturnCountAs6(){
        Product apple =   Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        shoppingCartService.addProductToCart(apple,6);
        assertEquals(6, shoppingCartService.getCart().get(apple).getQuantity());
    }

    @Test
    public void addProductToCart_WhenAdding2ApplesTwice_shouldReturnCountAs4(){
        Product apple =   Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        shoppingCartService.addProductToCart(apple,2);
        shoppingCartService.addProductToCart(apple,2);
        assertEquals(4, shoppingCartService.getCart().get(apple).getQuantity());
    }

}