package com.javatest.shop.service;

import com.javatest.shop.model.Product;
import com.javatest.shop.model.Unit;
import com.javatest.shop.repos.InMemoryProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductListingService.class, InMemoryProductRepo.class})
public class ProductListingServiceTest {

    @Autowired
    private ProductListingService service;

    @Test
    public void listAllAvailableProducts_ShouldListAllAvialableProducts() {
        String expectedResult = "------------Add Products Menu-------------\n" +
                "1.Apple\n" +
                "2.Bread\n" +
                "3.Milk\n" +
                "4.Soup\n" +
                "x. Previous Menu\n" +
                "To add enter corresponding product number or x to go back to main menu :";
        assertEquals(expectedResult, service.listAllAvailableProducts());
    }

    @Test
    public void validateInputAndReturnProduct_WhenInputIs1_ShouldReturnAppleProduct() {
        Product expectedProduct = Product
                .builder()
                .productName("Apple")
                .productUnit(Unit.APPLE)
                .productUnitPrice(BigDecimal.valueOf(.10))
                .build();
        Product actualProduct = service.validateInputAndReturnProduct(1);
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void validateInputAndReturnProduct_WhenInputIsminus1_ShouldReturnNullProduct() {
        assertNull(service.validateInputAndReturnProduct(-1));
    }

    @Test
    public void validateInputAndReturnProduct_WhenInputIsGreaterThanListSize_ShouldReturnNullProduct() {
        assertNull(service.validateInputAndReturnProduct(5));
    }

}