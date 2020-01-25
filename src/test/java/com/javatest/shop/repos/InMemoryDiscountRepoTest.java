package com.javatest.shop.repos;

import com.javatest.shop.model.Discount;
import com.javatest.shop.model.PercentageDiscount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InMemoryDiscountRepo.class)
class InMemoryDiscountRepoTest {

    @Autowired
    DiscountRepo discountRepo;

    @Test
    public void getDiscountByProductName_WhenValidProductNameApple_ShouldReturnAppleDiscount(){
        Discount expectedDiscount   =   PercentageDiscount
                .builder()
                .discountedProductName("Apple")
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(6))
                .discountPercentage(BigDecimal.valueOf(10))
                .build();
        Discount actualDiscount =   discountRepo.getDiscountByProductName("Apple").get();
        assertEquals(expectedDiscount.getDiscountedProductName(), actualDiscount.getDiscountedProductName());
        assertEquals(expectedDiscount.getStartDate(), actualDiscount.getStartDate());
        assertEquals(expectedDiscount.getEndDate(), actualDiscount.getEndDate());
        assertEquals(((PercentageDiscount)expectedDiscount).getDiscountPercentage(), ((PercentageDiscount)actualDiscount).getDiscountPercentage());

    }

    @Test
    public void getDiscountByProductName_WhenProductNameNotDiscountedBread_ShouldReturnEmptyDiscount(){
        assertFalse(discountRepo.getDiscountByProductName("Bread").isPresent());
    }

    @Test
    public void getDiscountByProductName_WhenProductNameNull_ShouldReturnEmptyDiscount(){
        assertFalse(discountRepo.getDiscountByProductName(null).isPresent());
    }


}