package app.ui.gui.roles;

import app.ui.gui.AppGUI;
import app.ui.gui.LoginUI;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUI implements Initializable {

    private AppGUI mainAppGUI;
    private LoginUI loginUI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setMainApp(AppGUI mainAppGUI) {
        this.mainAppGUI = mainAppGUI;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public AppGUI getMainApp() {
        return this.mainAppGUI;
    }
}
