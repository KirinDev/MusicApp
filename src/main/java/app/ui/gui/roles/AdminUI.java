package app.ui.gui.roles;

import app.ui.gui.App;
import app.ui.gui.LoginUI;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUI implements Initializable {

    private App mainApp;
    private LoginUI loginUI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public App getMainApp() {
        return this.mainApp;
    }
}
