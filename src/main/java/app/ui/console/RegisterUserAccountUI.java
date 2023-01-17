package app.ui.console;

import app.controller.RegisterUserAccController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RegisterUserAccountUI implements Runnable {

    private RegisterUserAccController ctrl;

    public RegisterUserAccountUI() {
        ctrl = new RegisterUserAccController();
    }

    public void run() {
        System.out.println("| User Registration |");
        String name = Utils.readLineFromConsole("Username: ");
        String email = Utils.readLineFromConsole("Email: ");
        String pwd = Utils.readLineFromConsole("Password: ");

        if( dataConfirmation(name, email, pwd).equals("yes")) {
            if( ctrl.registerUser(name, email, pwd, Constants.ROLE_USER)) {
                System.out.println("\n< The User account has been registered successfully >");
            }else{
                System.err.println("\n« The data introduced is invalid. Try again »");
            }
        }
    }

    public String dataConfirmation(String name, String email, String pwd) {
        List<String> options = new ArrayList<>();
        options.add("yes");
        options.add("no");

        System.out.println("=========User Registration Confirmation========");
        System.out.println("Username: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + pwd);
        System.out.println("=======================//======================");

        int option = Utils.showAndSelectIndex(options, "\n\nConfirm data:");
        return options.get(option);
    }
}
