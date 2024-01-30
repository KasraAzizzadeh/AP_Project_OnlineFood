package Interface;

import Data.Admin;
import Data.Customer;
import Data.Restaurant;
import Data.User;

import java.util.regex.Matcher;

public class LoginMenu {

    private UserInterface userInterface;
    public LoginMenu(UserInterface userInterface){

        this.userInterface = userInterface;
    }

    public String run(){
        String command;
        String result;
        boolean flag;

        while (true){
            flag = true;
            command = Input.getScanner().nextLine();

            //exit app
            if (command.matches("^\\s*exit\\s*$"))
                return "exit";
            //show current menu
            if(command.matches("^\\s*show\\s+current\\s+menu\\s*$")){
                System.out.println("login menu");
                flag = false;
            }
            //register a new Customer
            else if (command.matches("^\\s*register\\s*$")){
                flag = false;
                System.out.print("please enter your username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("please enter your password: ");
                    String password = Input.getScanner().nextLine();
                    if (password.matches("^\\s*\\S+\\s*$")){
                        password = password.trim();
                        result = register(username, password);
                    }
                    else
                        result = "register failed: invalid password format";
                }
                else
                    result = "register failed: invalid username format";
                System.out.println(result);
            }
            //login as Customer
            else if (command.matches("^\\s*login\\s+as\\s+customer\\s*$")){
                flag = false;
                System.out.print("please enter your username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("please enter your password: ");
                    String password = Input.getScanner().nextLine();
                    if (password.matches("^\\s*\\S+\\s*$")){
                        password = password.trim();
                        result = customerLogin(username, password);
                        if (result.equals("login successful")){
                            userInterface.currentCustomer = Customer.getCustomerByUsername(username);
                            System.out.println(result);
                            return "Customer Menu";
                        }
                    }
                    else
                        result = "login failed: invalid password format";
                }
                else
                    result = "login failed: invalid username format";
                System.out.println(result);
            }
            //login as Restaurant
            else if (command.matches("^\\s*login\\s+as\\s+restaurant\\s*$")){
                flag = false;
                System.out.print("please enter your username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("please enter your password: ");
                    String password = Input.getScanner().nextLine();
                    if (password.matches("^\\s*\\S+\\s*$")){
                        password = password.trim();
                        result = restaurantLogin(username, password);
                        if (result.equals("login successful")){
                            userInterface.currentRestaurant = Restaurant.getRestaurantByUsername(username);
                            System.out.println(result);
                            return "Restaurant Menu";
                        }
                    }
                    else
                        result = "login failed: invalid password format";
                }
                else
                    result = "login failed: invalid username format";
                System.out.println(result);
            }
            //login as Admin
            else if (command.matches("^\\s*login\\s+as\\s+admin\\s*$")){
                flag = false;
                System.out.print("please enter your username: ");
                String username = Input.getScanner().nextLine();
                if (username.matches("^\\s*\\S+\\s*$")){
                    username = username.trim();
                    System.out.print("please enter your password: ");
                    String password = Input.getScanner().nextLine();
                    if (password.matches("^\\s*\\S+\\s*$")){
                        password = password.trim();
                        result = adminLogin(username, password);
                        if (result.equals("login successful")){
                            userInterface.currentAdmin = Admin.getAdmin();
                            System.out.println(result);
                            return "Admin Menu";
                        }
                    }
                    else
                        result = "login failed: invalid password format";
                }
                else
                    result = "login failed: invalid username format";
                System.out.println(result);
            }
            //if none of commands are called it was an invalid command
            else if (flag)
                System.out.println("invalid command!");

        }
    }

    static String register(String username, String password){

        if(!username.matches(".*[a-zA-Z].*") || username.matches(".*[^a-zA-Z0-9_]+.*"))
            return "register failed: invalid username format";

        if(Customer.getCustomerByUsername(username) != null)
            return "register failed: username already exists";

        if(password.matches(".*[^a-zA-Z0-9_]+.*"))
            return "register failed: invalid password format";

        if(password.length() < 5 || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*"))
            return "register failed: weak password";

        Customer.addCustomer(username, password);
        return "register successful";
    }

    static String customerLogin(String username, String password){

        if (Customer.getCustomerByUsername(username) == null)
            return "login failed: this customer does not exist\nor maybe you are not a customer";

        if (!Customer.getCustomerByUsername(username).checkPassword(password))
            return "login failed: wrong password";

        return "login successful";
    }

    static String restaurantLogin(String username, String password){

        if (Restaurant.getRestaurantByUsername(username) == null)
            return "login failed: this restaurant does not exist\nor maybe you are not a restaurant";

        if (!Restaurant.getRestaurantByUsername(username).checkPassword(password))
            return "login failed: wrong password";

        return "login successful";
    }

    static String adminLogin(String username, String password){

        if (!Admin.getAdmin().checkUsername(username))
            return "login failed: this admin does not exist\nor maybe you are not an admin";

        if (!Admin.getAdmin().checkPassword(password))
            return "login failed: wrong password";

        return "login successful";
    }

}
