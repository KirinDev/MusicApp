package app.ui.gui.roles;

import app.ui.gui.AppGUI;
import app.ui.gui.LoginUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserUI implements Initializable {

    private AppGUI mainAppGUI;
    private LoginUI loginUI;

    @FXML
    private Label userEmail = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setMainApp(AppGUI mainAppGUI) {
        this.mainAppGUI = mainAppGUI;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public void setEmailLabel(String email) {
        StringProperty str = new SimpleStringProperty(email);
        this.userEmail.textProperty().bind(str);
    }

    public AppGUI getMainApp() {
        return this.mainAppGUI;
    }
}
