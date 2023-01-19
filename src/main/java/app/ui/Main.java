package app.ui;

import app.database.Driver;
import app.ui.console.MainMenuUI;

public class Main {

    public static void main(String[] args) {
        Driver driver = new Driver();

        try {
            driver.connectToSQLServer();

            MainMenuUI menu = new MainMenuUI();

            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
