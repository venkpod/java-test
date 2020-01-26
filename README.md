# Java Exercise

This is a simple exercise to allow you to demostrate your software engineering skillset. It's completly up to you how long you give yourself, stop when you're happy with the quality of your work, but we don't expect it to take too long.

This exersice is implemented using Java(1.8.0_212), it adhere the SOLID principles and interactive using command line. The build tool used is Gradle and built on Spring boot(2.2.4) framework

## Instructions to compile and run

  To Compile the application run below command:
  
  **$ gradle clean build**
  
  Which on sucessfully built the jar is built in to build/lib directory.
  
  To Run the application :
  
  **$ java -jar build/libs/java-test-0.0.1-SNAPSHOT.jar com.javatest.shop.GroceriesShoppingApplication**
  
  
## Specification

A local shop, Henry’s Grocery, has asked you to author an IT solution for them to price up a basket of shopping for their customers.

Henry’s Grocery, currently only stocks four items and has two promotions. These are as follows:

### Stock Items
        
|  **product** | **unit**   | **cost** |
| :---  | :---: | :---: |
|  soup    | tin    | 0.65 |
|  bread   | loaf   | 0.80 |
|  milk    | bottle | 1.30 |
|  apples  | single | 0.10 |

### Discounts
 
| **the offer**| **valid from** | **valid to** | 
| :---     | :---: | :---: |    
| Buy 2 tins of soup and get a loaf of bread half price | yesterday | for 7 days |
| Apples have a 10% discount | from 3 days hence | until the end of the following month |

### Inputs
 All basket items added via the command prompt.

### Outputs
All outputs must print to the command line.
     
### Tests
   - Price a basket containing: 3 tins of soup and 2 loaves of bread, bought today, 
     - Expected total cost = 3.15;
   - Price a basket containing: 6 apples and a bottle of milk, bought today, 
     - Expected total cost = 1.90;
   - Price a basket containing: 6 apples and a bottle of milk, bought in 5 days time,
     - Expected total cost = 1.84;
   - Price a basket containing: 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time,
     - Expected total cost = 1.97.
     
  
 
 ### Instructions to add product to shopping cart and checkout
 
 When application is started the Main menu is disaplyed with options to add product (option 1), checkout (option 2) and exit (option x) the application as shown below:
 
 ***
 
 >------------ Welcome to Henry’s Grocery Shop ------------------

 >Please Enter : 
 >1. Add products to basket 
 >2. Checkout and print total cost 
 
 >x. EXIT
 
 ***

 When entered option 1 the second menu displays the available Products and can choose based on the product number listed or 'x' to return to main menu.
 
 ***
 >------------Add Products Menu-------------
 
 >1. Apple
 >2. Bread
 >3. Milk
 >4. Soup
 
 >x. Previous Menu
 
 >To add enter corresponding product number or x to go back to main menu :
 ***
 
 Once the product is selected it prompts for the qantity of the products which are measured in product units.
 
 ***
 >Please Enter the no of Apple singles:
 ***
 
 Once addition of all the products is done return to main menu by entering 'x' . On the main menu enter '2' to get the Total price, before displaying the total price it prompts for the billing days, which is required to tbe entered in days (for ex : for Today enter 0, Tomorrow 1 and so on)
 
 ***
 >Billing Days from Today (as number 0 for Today, 1 for Tommorrow ....) :
 ***
 
 Once the valid days are entered the Total price is displayed and application is terminated.
 
 ***
 >Total Price :
 ***