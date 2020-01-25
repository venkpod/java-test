package com.javatest.shop;

import com.javatest.shop.exceptions.IncorrectInputException;
import com.javatest.shop.model.Product;
import com.javatest.shop.service.ProductListingService;
import com.javatest.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GroceriesShoppingApplication implements CommandLineRunner {


    private ProductListingService productListingService;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public GroceriesShoppingApplication(ProductListingService productListingService,
                                        ShoppingCartService shoppingCartService) {
        this.productListingService = productListingService;
        this.shoppingCartService = shoppingCartService;
    }

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
                        addProductToBasketUI(scanner);
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

    private void addProductToBasketUI(Scanner scanner) {
        while (true) {
            System.out.println(productListingService.listAllAvailableProducts());
            String option = scanner.nextLine();
            switch (option.toLowerCase()) {
                case "x":
                    return;
                default:
                    int input = 0;
                    try {
                        input = Integer.parseInt(option);
                        if (input < 1 || input > productListingService.numberOfProducts())
                            throw new IncorrectInputException(option);

                    } catch (NumberFormatException|IncorrectInputException ex) {
                        System.out.println("Invalid Entry, Please enter Valid entry from the given list");
                        continue;
                    }
                    Product product = productListingService.validateInputAndReturnProduct(input);
                    System.out.println("Please Enter the no of " + product.getProductName() + " " + product.getProductUnit().getUnit() + "s:");

                    int count = 0;
                    while (true) {
                        String countString = scanner.nextLine();
                        try {
                            count = Integer.parseInt(countString);
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid Entry, Please enter the quantitiy in numbers:");
                            continue;
                        }
                        break;
                    }
                    if (product != null) {
                        shoppingCartService.addProductToCart(product, count);
                    }
                    continue;
            }


        }
    }
}
