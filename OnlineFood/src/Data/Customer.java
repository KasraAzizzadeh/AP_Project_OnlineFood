package Data;

import java.util.ArrayList;

public class Customer extends User {

    private final static ArrayList<Customer> allCustomers  = new ArrayList<>();
    private Cart cart;

    private Customer(String username, String password){
        this.username = username;
        this.password = password;
        cart = new Cart();
        this.role = "Customer";
    }

    public static void addCustomer(String username, String password){
        allCustomers.add(new Customer(username, password));
    }

    public static Customer getCustomerByUsername(String username){
        for (Customer customer : allCustomers){
            if (customer.username.equals(username)){
                return customer;
            }
        }
        return null;
    }

    public void purchase(){
        if (cart.totalAmount > balance)
            System.out.println("purchase failed: not enough money");
        else {
            balance -= cart.totalAmount;
            cart.purchase();
        }
    }

    public void chargeBalance(int balance){
        this.balance += balance;
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

    public Cart getCart() {
        return cart;
    }
}
