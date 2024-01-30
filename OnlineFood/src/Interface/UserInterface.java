package Interface;

import Data.Admin;
import Data.Customer;
import Data.Restaurant;
import Data.User;

public class UserInterface {

    private final LoginMenu loginMenu;
    private final CustomerMenu customerMenu;
    private final RestaurantMenu restaurantMenu;
    private final AdminMenu adminMenu;

    Customer currentCustomer;
    Restaurant currentRestaurant;
    Admin currentAdmin;

    public UserInterface() {
        loginMenu = new LoginMenu(this);
        customerMenu = new CustomerMenu(this);
        restaurantMenu = new RestaurantMenu(this);
        adminMenu = new AdminMenu(this);
    }

    public void run(){
        System.out.println("Welcome to Online Food");
        while(true){
            switch (loginMenu.run()){
                case "Admin Menu":
                    adminMenu.run();
                    break;
                case "Restaurant Menu":
                    restaurantMenu.run();
                    break;
                case "Customer Menu":
                    customerMenu.run();
                    break;
                case "exit":
                    return;
            }
        }
    }

}
