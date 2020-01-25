package com.javatest.shop.repos;

import com.javatest.shop.model.Product;
import com.javatest.shop.model.Unit;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

/*
Inmemory Product repo
 */
@Repository
@NoArgsConstructor
public class InMemoryProductRepo implements ProductRepo {

    private List<Product> stock;

    /**
     * initialize with all available products
     */
    @PostConstruct
    public void initializeProducts() {
        List<Product> tempStock = new ArrayList<>();
        tempStock.add(0, Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build());
        tempStock.add(1, Product
                .builder()
                .productName("Bread")
                .productUnit(Unit.BREAD)
                .productUnitPrice(BigDecimal.valueOf(.80))
                .build());
        tempStock.add(2, Product
                .builder()
                .productName("Milk")
                .productUnit(Unit.MILK)
                .productUnitPrice(BigDecimal.valueOf(1.30))
                .build());
        tempStock.add(3, Product
                .builder()
                .productName("Soup")
                .productUnit(Unit.SOUP)
                .productUnitPrice(BigDecimal.valueOf(.65))
                .build());
        stock = Collections.unmodifiableList(tempStock);
    }


    @Override
    public Optional<Product> getProductByName(String productName) {
        if(productName != null)
            return stock.stream().filter(product -> product.getProductName().equalsIgnoreCase(productName)).findFirst();
        return Optional.empty();
    }

    @Override
    public List<Product> getAllAvailableProducts() {
        return stock;
    }
}
