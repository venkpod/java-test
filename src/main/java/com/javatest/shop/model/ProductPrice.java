package com.javatest.shop.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductPrice {
    private int quantity;
    private BigDecimal totalPrice;

    public ProductPrice setQuantity(int quantity) {
        this.quantity += quantity;
        return this;
    }
}
