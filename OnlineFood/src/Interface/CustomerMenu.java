package Interface;

import Data.Customer;
import Data.Food;
import Data.Restaurant;

public class CustomerMenu {

    private UserInterface userInterface;
    public CustomerMenu(UserInterface userInterface){
        this.userInterface = userInterface;
    }

    public void run(){
        String command;
        String result;
        boolean flag;

        while (true){
            flag = true;
            command = Input.getScanner().nextLine();

            //exit app
            if (command.matches("^\\s*logout\\s*$")){
                userInterface.currentCustomer = null;
                return;
            }
            //show current menu
            else if(command.matches("^\\s*show\\s+current\\s+menu\\s*$")){
                System.out.println("Customer menu");
                flag = false;
            }
            //search for food: show all restaurants
            else if(command.matches("^\\s*show\\s+all\\s+restaurants\\s*$")){
                Restaurant.showAllRestaurants();
                flag = false;
            }
            //search for food: show all of a restaurant's foods
            else if(command.matches("^\\s*show\\s+all\\s+restaurant\\s+foods\\s*$")){
                flag = false;
                System.out.print("enter restaurant username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    showRestaurantFoods(username);
                }
                else
                    System.out.println("show failed: invalid username format");
            }
            //search for food: show all the foods
            else if(command.matches("^\\s*show\\s+all\\s+foods\\s*$")){
                flag = false;
                Food.showAllFoods();
            }
            //search for food: finding a specific food
            else if(command.matches("^\\s*search\\s+for\\s+food\\s*$")){
                flag = false;
                System.out.print("enter food name: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                Food.searchForFood(name);
            }
            //check balance
            else if (command.matches("^\\s*check\\s+balance\\s*$")){
                int balance = userInterface.currentCustomer.checkBalance();
                System.out.println("your balance is: " + balance);
                flag = false;
            }
            //charge balance
            else if (command.matches("^\\s*charge\\s+balance\\s*$")){
                System.out.print("enter charge amount: ");
                int amount = Input.getScanner().nextInt();
                Input.getScanner().nextLine();
                if (amount <= 0)
                    System.out.println("charge amount can't be less than 1");
                else {
                    userInterface.currentCustomer.chargeBalance(amount);
                    System.out.println("your balance is: " + userInterface.currentCustomer.checkBalance());
                }
                flag = false;
            }
            //add food to cart
            else if (command.matches("^\\s*add\\s+food\\s*$")){
                System.out.print("enter food name: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                System.out.print("enter restaurant username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("enter number of foods: ");
                    int number = Input.getScanner().nextInt();
                    Input.getScanner().nextLine();
                    addToCart(name, username, number, userInterface.currentCustomer);
                }
                else
                    System.out.println("add failed: invalid username format");
                flag = false;
            }
            //view customer cart
            else if (command.matches("^\\s*view\\s+my\\s+cart\\s*$")){
                userInterface.currentCustomer.getCart().viewCart();
                flag = false;
            }
            //remove food from cart
            else if (command.matches("^\\s*remove\\s+food\\s*$")){
                System.out.print("enter food name: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                System.out.print("enter restaurant username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("enter number of foods: ");
                    int number = Input.getScanner().nextInt();
                    Input.getScanner().nextLine();
                    removeFromCart(name, username, number, userInterface.currentCustomer);
                }
                else
                    System.out.println("remove failed: invalid username format");
                flag = false;
            }
            //purchase
            else if (command.matches("^\\s*purchase\\s*$")){
                userInterface.currentCustomer.purchase();
                flag = false;
            }
            //add comment
            else if (command.matches("^\\s*add\\s+comment\\s*$")){
                System.out.print("enter food name: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                System.out.print("enter restaurant username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("enter your comment: ");
                    String comment = Input.getScanner().nextLine();
                    addComment(name, username, comment, userInterface.currentCustomer.getUsername());
                }
                else
                    System.out.println("remove failed: invalid username format");
                flag = false;
            }
            //change password
            //if command not recognized: invalid command
            else if (flag)
                System.out.println("invalid command!");
        }
    }

    static void showRestaurantFoods(String username){
        if (Restaurant.getRestaurantByUsername(username) == null)
            System.out.println("show failed: restaurant does not exist");

        else
            Restaurant.getRestaurantByUsername(username).showAllRestaurantFoods();
    }

    static void addToCart(String foodName, String username, int number, Customer customer){
        if (Restaurant.getRestaurantByUsername(username) == null)
            System.out.println("this restaurant does not exist");

        else if (Restaurant.getRestaurantByUsername(username).getFoodByName(foodName) == null)
            System.out.println("this restaurant does not have this food");

        else {
            customer.getCart().addFoodToCart(Restaurant.getRestaurantByUsername(username).getFoodByName(foodName), number);
            System.out.println(number + " of " + foodName + " were successfully added to your cart");
        }
    }

    static void removeFromCart(String foodName, String username, int number, Customer customer){
        if (Restaurant.getRestaurantByUsername(username) == null)
            System.out.println("this restaurant does not exist");

        else if (Restaurant.getRestaurantByUsername(username).getFoodByName(foodName) == null)
            System.out.println("this restaurant does not have this food");

        else if (customer.getCart().checkCart(Restaurant.getRestaurantByUsername(username).getFoodByName(foodName)) == 0)
            System.out.println("you don't have this food in your cart");

        else {
            customer.getCart().removeFoodFromCart(Restaurant.getRestaurantByUsername(username).getFoodByName(foodName), number);
            System.out.println(number + " of " + foodName + " were successfully removed from your cart");
        }
    }

    static void addComment(String foodName, String username, String comment, String customerUsername){
        if (Restaurant.getRestaurantByUsername(username) == null)
            System.out.println("this restaurant does not exist");

        else if (Restaurant.getRestaurantByUsername(username).getFoodByName(foodName) == null)
            System.out.println("this restaurant does not have this food");

        else {
            Restaurant.getRestaurantByUsername(username).getFoodByName(foodName).addComment(comment, customerUsername);
            System.out.println("your comment was successfully added");
        }
    }

}
