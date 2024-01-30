import Data.Admin;
import Interface.UserInterface;

public class Main {
    public static void main(String[] args) {

        //admin creation
        String username = "OnlineAdmin";
        String password = "55Kasra55";

        Admin.createAdmin(username, password);
        //UI run
        UserInterface ui = new UserInterface();
        ui.run();
    }
}