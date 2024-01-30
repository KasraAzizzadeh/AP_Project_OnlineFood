package Interface;

import Data.Food;
import Data.Restaurant;

public class RestaurantMenu {

    private UserInterface userInterface;
    public RestaurantMenu(UserInterface userInterface){

        this.userInterface = userInterface;
    }

    public void run(){
        String command;
        String result;
        boolean flag;

        while (true){
            flag = true;
            command = Input.getScanner().nextLine();

            //logout
            if (command.matches("^\\s*logout\\s*$")){
                userInterface.currentRestaurant = null;
                return;
            }
            //show current menu
            else if(command.matches("^\\s*show\\s+current\\s+menu\\s*$")){
                System.out.println("Restaurant menu");
                flag = false;
            }
            //adding food to the menu
            else if (command.matches("^\\s*add\\s+food\\s*$")){
                System.out.print("enter food name: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                System.out.print("enter food price: ");
                int price = Input.getScanner().nextInt();
                Input.getScanner().nextLine();
                if (price > 0){
                    result = addFood(name, price, userInterface.currentRestaurant);
                }
                else
                    result = "add failed: price can't be less than 1";
                System.out.println(result);
                flag = false;
            }
            //change food price
            else if (command.matches("^\\s*change\\s+price\\s*$")){
                System.out.print("enter food name to change price: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                System.out.print("enter new food price: ");
                int newPrice = Input.getScanner().nextInt();
                Input.getScanner().nextLine();
                if (newPrice > 0){
                    result = changePrice(name, newPrice, userInterface.currentRestaurant);
                }
                else
                    result = "change failed: price can't be less than 1";
                System.out.println(result);
                flag = false;
            }
            //show all foods
            else if(command.matches("^\\s*show\\s+foods\\s*$")){
                userInterface.currentRestaurant.showAllRestaurantFoods();
                flag = false;
            }
            //remove food
            else if (command.matches("^\\s*remove\\s+food\\s*$")){
                System.out.print("enter food name to remove: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                result = removeFood(name, userInterface.currentRestaurant);
                System.out.println(result);
                flag = false;
            }
            //check balance
            else if (command.matches("^\\s*check\\s+balance\\s*$")){
                int balance = userInterface.currentRestaurant.checkBalance();
                System.out.println("your balance is: " + balance);
                flag = false;
            }
            //check food rating
            else if (command.matches("^\\s*check\\s+food\\s+rating\\s*$")){
                System.out.print("enter food name to check: ");
                String name = Input.getScanner().nextLine();
                name = name.trim();
                checkFoodRating(name, userInterface.currentRestaurant);
                flag = false;
            }
            //change password
            else if (command.matches("^\\s*change\\s+password\\s*$")){
                flag = false;
                System.out.print("enter old password: ");
                String oldPassword = Input.getScanner().nextLine();
                if (oldPassword.matches("^\\s*\\S+\\s*$")){
                    oldPassword = oldPassword.trim();
                    System.out.print("enter new password: ");
                    String newPassword = Input.getScanner().nextLine();
                    if (newPassword.matches("^\\s*\\S+\\s*$")){
                        newPassword = newPassword.trim();
                        result = changeRestaurantPassword(oldPassword, newPassword, userInterface.currentRestaurant);
                    }
                    else
                        result = "change failed: invalid password format";
                }
                else
                    result = "change failed: invalid username format";
                System.out.println(result);
            }
            //if command not recognized: invalid command
            else if (flag)
                System.out.println("invalid command!");
        }
    }

    static String addFood(String name, int price, Restaurant restaurant){
        if (restaurant.getFoodByName(name) != null)
            return "add failed: food already exists";

        restaurant.addFood(name, price);
        return "add successful";
    }

    static String changePrice(String name, int newPrice, Restaurant restaurant){
        if (restaurant.getFoodByName(name) == null)
            return "change failed: food does not exist";

        if (restaurant.getFoodByName(name).getPrice() == newPrice)
            return "change failed: new price is the same";

        restaurant.getFoodByName(name).setPrice(newPrice);
        Food.getFoodByName(name, restaurant).setPrice(newPrice);
        return (restaurant.getFoodByName(name).getName() + " price changed to " + newPrice);
    }

    static String removeFood(String name, Restaurant restaurant){
        if (restaurant.getFoodByName(name) == null)
            return "change failed: food does not exist";

        restaurant.removeFood(name);
        Food.removeFood(name, restaurant);
        return "food removal successful";
    }

    static void checkFoodRating(String name, Restaurant restaurant){
        if (restaurant.getFoodByName(name) == null)
            System.out.println("check failed: food does not exist");

        else
            restaurant.getFoodByName(name).viewRatings();
    }

    static String changeRestaurantPassword(String oldPassword, String newPassword, Restaurant restaurant){
        if (!restaurant.checkPassword(oldPassword))
            return "change failed: old password is wrong";

        if(newPassword.matches(".*[^a-zA-Z0-9_]+.*"))
            return "change failed: invalid new password format";

        if(newPassword.length() < 5 || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*"))
            return "change failed: weak new password";

        restaurant.changePassword(newPassword);
        return ("change successful, your new password is: " + newPassword);
    }
}
