package com.javatest.shop.service;

import com.javatest.shop.model.Discount;
import com.javatest.shop.model.Product;
import com.javatest.shop.model.ProductPrice;
import com.javatest.shop.repos.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * Billing service which is responsible for applying the Discount is available and calculate the total price for the products added to cart
 */
@Service
public class BillingService {

    private DiscountRepo discountRepo;

    @Autowired
    public BillingService(DiscountRepo discountRepo) {
        this.discountRepo = discountRepo;
    }

    public BigDecimal getTotalPrice(Map<Product, ProductPrice> cart, LocalDate billingDate) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        applyDiscount(cart, billingDate);
        for (Map.Entry<Product, ProductPrice> entry : cart.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    private void applyDiscount(Map<Product, ProductPrice> cart, LocalDate billingDate) {
        cart.forEach((product, productPrice) -> {

            Optional<Discount> optDiscount = discountRepo.getDiscountByProductName(product.getProductName());

            optDiscount.ifPresent(discount -> {
                if ((billingDate.isEqual(discount.getStartDate()) || billingDate.isAfter(discount.getStartDate())) &&
                        (billingDate.isEqual(discount.getEndDate()) || billingDate.isBefore(discount.getEndDate())))
                    discount.applyDiscount(cart);
            });
            if (productPrice.getTotalPrice() == null)
                productPrice.setTotalPrice(BigDecimal.valueOf(productPrice.getQuantity()).multiply(product.getProductUnitPrice()));

        });
    }


}
