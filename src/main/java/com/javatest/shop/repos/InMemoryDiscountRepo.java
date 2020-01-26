package com.javatest.shop.repos;

import com.javatest.shop.model.CombinationDiscount;
import com.javatest.shop.model.Discount;
import com.javatest.shop.model.PercentageDiscount;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/*
 * Repo which does inmemory initialization of Discounts
 */
@Repository
public class InMemoryDiscountRepo implements DiscountRepo {

    private List<Discount> discounts;

    /**
     * initialize with all available discounts
     */
    @PostConstruct
    public void initializeProducts() {
        discounts = new ArrayList<>();

        Discount appleDiscount = PercentageDiscount
                .builder()
                .discountedProductName("Apple")
                .startDate(LocalDate.now().plusDays(3))
                .endDate(getAppleDiscountEndDate(LocalDate.now()))
                .discountPercentage(BigDecimal.valueOf(10))
                .build();
        discounts.add(appleDiscount);
        Discount soupDiscount   =   CombinationDiscount
                .builder()
                .discountedProductName("Soup")
                .discountOnProductName("Bread")
                .discountPricePercentage((BigDecimal.valueOf(50)))
                .qualifyingProdMinQnty(2)
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(6))
                .build();
        discounts.add(soupDiscount);
    }

    @Override
    public Optional<Discount> getDiscountByProductName(String productName) {
        if(productName != null)
            return discounts.stream().filter(discount -> discount.getDiscountedProductName().equalsIgnoreCase(productName)).findFirst();
        return Optional.empty();
    }

    public LocalDate getAppleDiscountEndDate(LocalDate fromDate){
        LocalDate endDateForAppleDiscount  = fromDate.plusMonths(1);
        endDateForAppleDiscount = endDateForAppleDiscount.withDayOfMonth(
                endDateForAppleDiscount.getMonth().length(endDateForAppleDiscount.isLeapYear()));

        return endDateForAppleDiscount;
    }
}
