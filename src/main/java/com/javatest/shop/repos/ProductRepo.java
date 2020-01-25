package com.javatest.shop.repos;

import com.javatest.shop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo {

    public Optional<Product> getProductByName(String product);

    public List<Product> getAllAvailableProducts();
}
