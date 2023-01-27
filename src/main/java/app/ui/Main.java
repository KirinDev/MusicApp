package app.ui;

import app.database.DataLoader;
import app.database.Driver;
import app.ui.console.MainMenuUI;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Driver driver = new Driver();
        DataLoader dataLoader = new DataLoader();

        try {
            Connection conn = driver.connectToSQLServer();
            dataLoader.loadData(conn);

            MainMenuUI menu = new MainMenuUI();

            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
