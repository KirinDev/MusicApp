package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainMenuUI {

    public MainMenuUI() { }

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        System.out.println("Welcome, please choose an option: ");
        options.add(new MenuItem("Do Login", new AuthUI() ));
        options.add(new MenuItem("Register Account", new RegisterUserAccountUI() ));

        int option = 0;

        do {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nMain Menu");
                if ( (option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            }
            catch (Exception e){
                System.out.print("Invalid Option!Try Again!");
            }
        }
        while (option != -1 );
    }
}
