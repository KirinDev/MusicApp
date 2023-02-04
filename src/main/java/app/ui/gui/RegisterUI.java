package app.ui.gui;

import app.controller.AuthController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUI implements Initializable {

    private App mainApp;
    private AuthController authController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authController = new AuthController();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public App getMainApp() {
        return this.mainApp;
    }
}
