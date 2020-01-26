package com.javatest.shop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Getter
public class CombinationDiscount extends Discount {

    @NonNull
    private Integer qualifyingProdMinQnty;
    @NonNull
    private String discountOnProductName;
    @NonNull
    private BigDecimal discountPricePercentage;

    @Builder
    public CombinationDiscount(String discountedProductName,
                               LocalDate startDate,
                               LocalDate endDate,
                               Integer qualifyingProdMinQnty,
                               String discountOnProductName,
                               BigDecimal discountPricePercentage) {
        super(discountedProductName, startDate, endDate);
        this.discountOnProductName = discountOnProductName;
        this.qualifyingProdMinQnty = qualifyingProdMinQnty;
        this.discountPricePercentage = discountPricePercentage;
    }

    @Override
    public void applyDiscount(Map<Product, ProductPrice> cart) {
        if (this.qualifyingProdMinQnty > 0) { // as it a combi discount, expects min 1

            //get the CombiProduct
            Optional<Map.Entry<Product, ProductPrice>> optionalDiscountProductProductPriceEntry = cart.entrySet()
                    .stream()
                    .filter(productProductPriceEntry ->
                            productProductPriceEntry
                                    .getKey()
                                    .getProductName()
                                    .equalsIgnoreCase(this.getDiscountedProductName()))
                    .findFirst();

            optionalDiscountProductProductPriceEntry.ifPresent(productProductPriceEntry -> {
                ProductPrice combiProductPrice = productProductPriceEntry.getValue();
                //calculate the number of combinations
                int combination = BigDecimal.valueOf(combiProductPrice.getQuantity())
                        .divideToIntegralValue(BigDecimal.valueOf(this.qualifyingProdMinQnty)).intValue();

                //get the eligible Product
                Optional<Map.Entry<Product, ProductPrice>> optionalQualProductProductPriceEntry = cart.entrySet()
                        .stream()
                        .filter(qualProductProductPriceEntry ->
                                qualProductProductPriceEntry
                                        .getKey()
                                        .getProductName()
                                        .equalsIgnoreCase(this.getDiscountOnProductName()))
                        .findFirst();

                optionalQualProductProductPriceEntry.ifPresent(discountProductProductPriceEntry -> {
                    Product eligibleProduct = discountProductProductPriceEntry.getKey();
                    ProductPrice eligibleProductPrice = discountProductProductPriceEntry.getValue();
                    BigDecimal hundred = BigDecimal.valueOf(100);

                    //get the minimum discount eligible count and calculate discount price
                    int eligibleDiscountCount = Math.min(combination, eligibleProductPrice.getQuantity());
                    BigDecimal discountPrice = eligibleProduct.getProductUnitPrice()
                            .multiply(this.discountPricePercentage)
                            .divide(hundred)
                            .multiply(new BigDecimal(eligibleDiscountCount));

                    // set the total price after discount
                    eligibleProductPrice.setTotalPrice(BigDecimal.valueOf(eligibleProductPrice.getQuantity())
                            .multiply(eligibleProduct.getProductUnitPrice())
                            .subtract(discountPrice));
                });

            });
        }
    }
}
