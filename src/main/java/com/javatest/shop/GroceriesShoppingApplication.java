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

/**
 * Main class to start the application which is interactive by taking the inputs from the command prompt
 */
@SpringBootApplication
public class GroceriesShoppingApplication implements CommandLineRunner {


    private ProductListingService productListingService;

    private ShoppingCartService shoppingCartService;

    private BillingService billingService;


    @Autowired
    public GroceriesShoppingApplication(ProductListingService productListingService,
                                        ShoppingCartService shoppingCartService,
                                        BillingService billingService) {
        this.productListingService = productListingService;
        this.shoppingCartService = shoppingCartService;
        this.billingService = billingService;
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
                        //check the cart if empty prompt the main menu
                        if(shoppingCartService.getCart().isEmpty()){
                            System.out.println("Shopping cart is empty, please add the products by selecting option 1\n\n");
                            continue;
                        }
                        //else continue with the billing days
                        System.out.println("Billing Days from Today (as number 0 for Today, 1 for Tommorrow ....) :");
                        int numOfDaysInt = 0;
                        //check the valid entry and prompts until the input is valid.
                        while (true) {
                            String numberOfDays = scanner.nextLine();
                            try {
                                numOfDaysInt = Integer.parseInt(numberOfDays);
                            } catch (NumberFormatException nfe) {
                                System.out.println("Invalid Entry, Please enter the days in numbers:");
                                continue;
                            }
                            break;
                        }
                        //Display the total price
                        System.out.println("Total Price : " + billingService.getTotalPrice(
                                shoppingCartService.getCart(),
                                LocalDate.now().plusDays(numOfDaysInt))
                                .setScale(2).toPlainString()
                        );
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
                case "x": //to exit
                    return;
                default: //anything else
                    //parse the input for the product
                    int input = 0;
                    try {
                        input = Integer.parseInt(option);
                        if (input < 1 || input > productListingService.numberOfProducts())
                            throw new IncorrectInputException(option);

                    } catch (NumberFormatException | IncorrectInputException ex) {
                        //if entered invalid entry
                        System.out.println("Invalid Entry, Please enter Valid entry from the given list");
                        continue;
                    }

                    Product product = productListingService.validateInputAndReturnProduct(input);

                    //prompting for the quantity of the product
                    System.out.println("Please Enter the no of " + product.getProductName() + " " + product.getProductUnit().getUnit() + "s:");
                    // parse the input if enter valid integer
                    int count = 0;
                    //inner loop to get the correct quantity
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
                    //add product to shopping cart
                    if (product != null) {
                        shoppingCartService.addProductToCart(product, count);
                    }
                    continue;
            }


        }
    }
}
