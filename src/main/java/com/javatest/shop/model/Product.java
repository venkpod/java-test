package com.javatest.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class Product implements Comparable<Product> {

    private String productName;

    private Unit productUnit;

    private BigDecimal productUnitPrice;

    @Override
    public int compareTo(Product product) {
        return this.getProductName().compareTo(product.getProductName());
    }
}
