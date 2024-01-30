package Data;

import java.util.ArrayList;

public class Restaurant extends User{

    private final static ArrayList<Restaurant> allRestaurants = new ArrayList<>();
    private final ArrayList<Food> foods;

    private Restaurant(String username, String password){
        this.username = username;
        this.password = password;
        this.role = "Restaurant";
        foods = new ArrayList<>();
    }

    public static void addRestaurant(String username, String password){
        allRestaurants.add(new Restaurant(username, password));
    }

    public static Restaurant getRestaurantByUsername(String username){
        for (Restaurant restaurant : allRestaurants){
            if (restaurant.username.equals(username))
                return restaurant;
        }
        return null;
    }

    public static void showAllRestaurants(){
        int counter = 1;
        for (Restaurant restaurant : allRestaurants){
            System.out.println(counter + ") " + restaurant.username);
            counter++;
        }
        if (counter == 1)
            System.out.println("there are no restaurants");
    }

    public void showAllRestaurantFoods(){
        int counter = 1;
        for (Food food : foods){
            System.out.println(counter + ") name: " + food.getName() + " - price: " + food.getPrice());
            counter++;
        }
        if (counter == 1)
            System.out.println("there are no foods");
    }

    public static void removeRestaurant(String username){
        for (int i = 0; i < allRestaurants.size(); i++){
            if (allRestaurants.get(i).username.equals(username)){
                allRestaurants.remove(i);
                break;
            }
        }
    }

    public void addFood(String name, int price){
        Food food = new Food(name, price, this);
        this.foods.add(food);
        Food.allFoods.add(food);
    }

    public void removeFood(String name){
        for (int i = 0; i < foods.size(); i++){
            if (foods.get(i).getName().equals(name)){
                foods.remove(i);
                break;
            }
        }
    }

    public Food getFoodByName(String name){
        for (Food food : foods){
            if (food.getName().equals(name))
                return food;
        }
        return null;
    }

    @Override
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public int checkBalance() {
        return this.balance;
    }

    public void chargeBalance(int balance){
        this.balance += balance;
    }

}
