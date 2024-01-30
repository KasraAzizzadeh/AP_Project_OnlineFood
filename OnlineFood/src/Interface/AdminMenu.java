package Interface;

import Data.Admin;
import Data.Customer;
import Data.Restaurant;
import Data.User;

import java.util.regex.Matcher;

public class AdminMenu {

    private UserInterface userInterface;
    public AdminMenu(UserInterface userInterface){
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
                userInterface.currentAdmin = null;
                return;
            }
            //show current menu
            else if(command.matches("^\\s*show\\s+current\\s+menu\\s*$")){
                System.out.println("Admin menu");
                flag = false;
            }
            //add restaurants
            else if (command.matches("^\\s*add\\s+a\\s+restaurant\\s*$")){
                flag = false;
                System.out.print("enter restaurant username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("enter default password: ");
                    String password = Input.getScanner().nextLine();
                    if (password.matches("^\\s*\\S+\\s*$")){
                        password = password.trim();
                        result = addRestaurant(username, password);
                    }
                    else
                        result = "add failed: invalid password format";
                }
                else
                    result = "add failed: invalid username format";
                System.out.println(result);
            }
            //show all restaurants
            else if(command.matches("^\\s*show\\s+all\\s+restaurants\\s*$")){
                Restaurant.showAllRestaurants();
                flag = false;
            }
            //removing restaurant
            else if (command.matches("^\\s*remove\\s+a\\s+restaurant\\s*$")){
                flag = false;
                System.out.print("enter restaurant username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    result = removeRestaurant(username);
                }
                else
                    result = "remove failed: invalid username format";
                System.out.println(result);
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
                        result = changeAdminPassword(oldPassword, newPassword, userInterface.currentAdmin);
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
                System.out.println("invalid command");
        }
    }

    static String addRestaurant(String username, String password){
        if(!username.matches(".*[a-zA-Z].*") || username.matches(".*[^a-zA-Z0-9_]+.*"))
            return "add failed: invalid username format";

        if(Restaurant.getRestaurantByUsername(username) != null)
            return "add failed: username already exists";

        if(password.matches(".*[^a-zA-Z0-9_]+.*"))
            return "add failed: invalid password format";

        if(password.length() < 5 || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*"))
            return "add failed: weak password";

       Restaurant.addRestaurant(username, password);
       return "adding restaurant successful";
    }

    static String removeRestaurant(String username){
        if (Restaurant.getRestaurantByUsername(username) == null)
            return "remove failed: this restaurant does not exist";

        Restaurant.removeRestaurant(username);
        return "remove successful";
    }

    static String changeAdminPassword(String oldPassword, String newPassword, Admin admin){
        if (!admin.checkPassword(oldPassword))
            return "change failed: old password is wrong";

        if(newPassword.matches(".*[^a-zA-Z0-9_]+.*"))
            return "change failed: invalid new password format";

        if(newPassword.length() < 5 || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*"))
            return "change failed: weak new password";

        admin.changePassword(newPassword);
        return ("change successful, your new password is: " + newPassword);
    }

}
