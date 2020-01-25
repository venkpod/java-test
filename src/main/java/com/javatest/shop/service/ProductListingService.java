package com.javatest.shop.service;

import com.javatest.shop.model.Product;
import com.javatest.shop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ProductListingService {

    private ProductRepo productRepo;

    @Autowired
    public ProductListingService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    public String listAllAvailableProducts() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Product> lstOfProducts = productRepo.getAllAvailableProducts();
        stringBuilder
                .append("------------Add Products Menu-------------\n");
        int i = 1;
        IntStream.range(1, lstOfProducts.size()+1)
                .forEach(idx -> {
                    stringBuilder
                            .append(idx).append(".").append(lstOfProducts.get(idx - 1).getProductName()).append("\n");
                });
        stringBuilder.append("x:Previous Menu\n");
        stringBuilder.append("To add Enter corresponding Number :");
        return stringBuilder.toString();
    }

    public Product validateInputAndReturnProduct(int input) {
        List<Product> lstOfProducts = productRepo.getAllAvailableProducts();
        return (input-1 > -1 && input-1 < lstOfProducts.size()) ? lstOfProducts.get(input-1) : null;
    }

    public int numberOfProducts(){
        return productRepo.getAllAvailableProducts().size();
    }
}
