package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {

    public Driver() { }

    public Connection connectToSQLServer() {
        Connection conn = null;

        try {
            String dbURL = "jdbc:sqlserver://;serverName=DESKTOP-OG8MBAB\\SQLKIRIN;instanceName=SQLKIRIN;database=db_MusicApp;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(dbURL);

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
