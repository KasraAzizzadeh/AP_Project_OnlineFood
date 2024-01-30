package Data;

import java.util.ArrayList;

public class Food {

    private final String name;
    private final Restaurant restaurant;
    private int price;
    private float rating;
    private int totalRatings;
    private final ArrayList<Comment> thisFoodComments = new ArrayList<>();
    final static ArrayList<Food> allFoods = new ArrayList<>();

    public Food(String name, int price, Restaurant restaurant){
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
        this.rating = 0;
        this.totalRatings = 0;
    }

    public static void showAllFoods(){
        int counter = 1;
        for (Food food : allFoods){
            System.out.println(counter + ") name: " + food.name + " - price: " + food.price + " - restaurant: " + food.restaurant.username);
            counter++;
        }
        if (counter == 1)
            System.out.println("there are no foods");
    }

    public static void searchForFood(String name){
        int counter = 1;
        for (Food food : allFoods){
            if (food.name.equals(name)){
                System.out.println(counter + ") price: " + food.price + " - restaurant: " + food.restaurant.username);
                counter++;
            }
        }
        if (counter == 1)
            System.out.println("there are no foods with this name");
    }

    public static void removeFood(String name, Restaurant restaurant){
        for (int i = 0; i < allFoods.size(); i++){
            if (allFoods.get(i).getName().equals(name) && allFoods.get(i).getRestaurant().equals(restaurant)){
                allFoods.remove(i);
                break;
            }
        }
    }

    public static Food getFoodByName(String name, Restaurant restaurant){
        for (Food food : allFoods){
            if (food.name.equals(name) && food.restaurant.equals(restaurant))
                return food;
        }
        return null;
    }

    public void addComment(String comment, String customerUsername){
        thisFoodComments.add(new Comment(comment, customerUsername));
    }

    public String getName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setPrice(int newPrice){
        this.price = newPrice;
    }

    public int getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }
}
