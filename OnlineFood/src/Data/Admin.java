package Data;

public class Admin extends User{

    private static Admin admin;

    private Admin(String username, String password){
        this.username = username;
        this.password = password;
        this.role = "Admin";
    }

    public static void createAdmin(String username, String password){
        admin = new Admin(username, password);
    }

    public static Admin getAdmin(){
        return admin;
    }

    public boolean checkUsername(String username){
        return this.username.equals(username);
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
}
