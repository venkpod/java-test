package com.javatest.shop.repos;

import com.javatest.shop.model.Product;
import com.javatest.shop.model.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InMemoryProductRepo.class)
class InMemoryProductRepoTest {

    @Autowired
    ProductRepo repos;

    @Test
    public void getProductByName_WithValidProductName_shouldReturnValidProduct() {
        Product expectedProduct = Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        Optional<Product> product = repos.getProductByName("Apple");
        assertTrue(product.isPresent());
        assertEquals(expectedProduct, product.get());
    }

    @Test
    public void getProductByName_WithInValidProductName_ShouldReturnNoProduct() {
        Optional<Product> product = repos.getProductByName("Noproduct");
        assertFalse(product.isPresent());
    }

    @Test
    public void getProductByName_WithNullProduct_ShouldReturnNoProduct() {
        Optional<Product> product = repos.getProductByName(null);
        assertFalse(product.isPresent());
    }


    @Test
    public void getAllAvailableProducts() {
        Product expectedProduct = Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        List<Product> lstOfProducts = repos.getAllAvailableProducts();
        assertEquals(4, lstOfProducts.size());
        assertEquals(expectedProduct, lstOfProducts.get(0));
    }
}