package app.controller;

import app.auth.AuthFacade;
import app.database.Driver;
import app.domain.model.User;
import app.domain.store.UserStore;

import java.sql.Connection;

public class RegisterUserAccController {

    private App app;

    public RegisterUserAccController() {
        this.app = App.getInstance();
    }

    public boolean registerUser(String name, String email, String pwd, String roleId) {
        AuthFacade auth = this.app.getKirinDev().getAuthFacade();
        return auth.addUserWithRole(name, email, pwd, roleId);
    }

    public Connection getServerConnection() {
        Driver driver = this.app.getKirinDev().getDriver();
        return driver.connectToSQLServer();
    }

    public void insertToDatabase(Connection conn, String name, String email, String password) {
        AuthFacade auth = this.app.getKirinDev().getAuthFacade();
        auth.insertUserDatabase(conn, name, email, password);
    }
}
