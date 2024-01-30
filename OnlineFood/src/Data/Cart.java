package Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    LinkedHashMap<Food, Integer> customerFoods;
    int totalAmount;

    Cart() {
        customerFoods = new LinkedHashMap<>();
        totalAmount = 0;
    }

    public void addFoodToCart(Food food, int number){
        int prevCount = checkCart(food);
        if (prevCount == 0){
            customerFoods.put(food, number);
            totalAmount += (food.getPrice() * number);
        }
        else if (prevCount > 0){
            customerFoods.replace(food, prevCount, prevCount + number);
            totalAmount += (food.getPrice() * number);
        }

    }

    public void viewCart(){
        int index = 1;
        for(Map.Entry<Food, Integer> entry : customerFoods.entrySet()){
            Food food = entry.getKey();
            int count = entry.getValue();
            System.out.println(index + ") name: " + food.getName() + " - restaurant: " + food.getRestaurant().username
                    + " - number: " + count + " - price: " + (food.getPrice() * count));
            index++;
        }
        System.out.println("Total: " + totalAmount);
    }

    public void removeFoodFromCart(Food food, int number){
        for(Map.Entry<Food, Integer> entry : customerFoods.entrySet()){
            Food cartFood = entry.getKey();
            int count = entry.getValue();
            if(cartFood.equals(food)){
                totalAmount -= food.getPrice() * number;
                if(number == count)
                    customerFoods.remove(food, count);
                else if(count > number)
                    customerFoods.replace(food, count, count - number);
                break;
            }
        }
    }

    public int checkCart(Food food){
        for(Map.Entry<Food, Integer> entry : customerFoods.entrySet()){
            Food cartFood = entry.getKey();
            int count = entry.getValue();
            if(cartFood.equals(food)){
                return count;
            }
        }
        return 0;
    }

    public void purchase(){
        ArrayList<Food> clone = new ArrayList<>();
        ArrayList<Integer> cloneNum = new ArrayList<>();
        for(Map.Entry<Food, Integer> entry : customerFoods.entrySet()){
            Food cartFood = entry.getKey();
            clone.add(cartFood);
            int count = entry.getValue();
            cloneNum.add(count);
            cartFood.getRestaurant().chargeBalance(cartFood.getPrice() * count);
        }
        for (int j = 0; j < clone.size(); j++)
            customerFoods.remove(clone.get(j), cloneNum.get(j));
        totalAmount = 0;
        System.out.println("purchase successful, enjoy your food");
    }
}
