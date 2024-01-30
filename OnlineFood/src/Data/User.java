package Data;

public abstract class User {

    protected String username;
    protected String password;
    protected String role;
    protected int balance;

    public abstract boolean checkPassword(String password);
    public abstract void changePassword(String newPassword);
    public abstract int checkBalance();
}
