package app.ui;

import app.database.DataLoader;
import app.database.Driver;
import app.ui.gui.AppGUI;

import java.sql.Connection;

public class MainGUI {

    public static void main(String[] args) {
        Driver driver = new Driver();
        DataLoader dataLoader = new DataLoader();

        try {
            Connection conn = driver.connectToSQLServer();
            dataLoader.loadData(conn);
            AppGUI.main(args);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
