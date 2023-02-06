package app.ui.gui;

import app.controller.AuthController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUI implements Initializable {

    private AppGUI mainAppGUI;
    private AuthController authController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authController = new AuthController();
    }

    public void setMainApp(AppGUI mainAppGUI) {
        this.mainAppGUI = mainAppGUI;
    }

    public AppGUI getMainApp() {
        return this.mainAppGUI;
    }
}
