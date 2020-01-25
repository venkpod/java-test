package com.javatest.shop;

import com.javatest.shop.exceptions.IncorrectInputException;
import com.javatest.shop.model.Product;
import com.javatest.shop.service.BillingService;
import com.javatest.shop.service.ProductListingService;
import com.javatest.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class GroceriesShoppingApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(GroceriesShoppingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("------------------------ Welcome to Henry’s Grocery Shop ----------------------------------");
            while (true) {
                System.out.println("Please Enter : \n" +
                        "1. Add products to basket \n" +
                        "2. Checkout and print total cost \n" +
                        "x. EXIT");
                String option = scanner.nextLine();
                switch (option.toLowerCase()) {
                    case "1":
                        System.out.println("Product added");
                        break;
                    case "2":
                        System.out.println("chekout");
                         return;
                    case "x":
                        return;
                    default:
                        System.out.println("Invalid entry, please re-enter '1' OR '2' OR 'x'");
                }
            }
        }
    }
}
