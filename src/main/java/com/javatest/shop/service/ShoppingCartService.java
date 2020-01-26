package com.javatest.shop.service;

import com.javatest.shop.model.Product;
import com.javatest.shop.model.ProductPrice;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  This service is reponsible for adding products to the shopping cart
 */
@Service
public class ShoppingCartService {

    @Getter
    private Map<Product, ProductPrice> cart   =   new HashMap<>();

    public void addProductToCart(Product product, int count){
        cart.compute(product, (k,v) -> v == null ? new ProductPrice(count,null): v.setQuantity(count));
    }
}
