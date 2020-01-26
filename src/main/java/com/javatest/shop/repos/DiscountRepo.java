package com.javatest.shop.repos;

import com.javatest.shop.model.Discount;
import com.javatest.shop.model.Product;

import java.util.List;
import java.util.Optional;

/**
 *  Discount Repository , which fetches the available discount for the given product name.
 */
public interface DiscountRepo {

    public Optional<Discount> getDiscountByProductName(String productName);
}
