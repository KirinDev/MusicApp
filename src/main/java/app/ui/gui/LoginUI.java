package app.ui.gui;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.mappers.dto.UserRoleDTO;
import app.ui.gui.roles.AdminUI;
import app.ui.gui.roles.UserUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {

    private App mainApp;
    private AuthController authController;
    private Stage stage;
    private UserRoleDTO role;

    @FXML
    private Button btnDoLogin;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;


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

    private void doLogin(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passwordField.getText();
        boolean success;
        if (!email.isBlank() && !password.isBlank()) {
            success = authController.doLogin(email, password);

            if (!success) {
                AlertUI.infoAlert("Invalid UserId and/or Password.", "Login Error");
            } else {
                List<UserRoleDTO> roles = this.authController.getUserRoles();
                if ((roles == null) || (roles.isEmpty())) {
                    AlertUI.infoAlert("Login Error", "User has no valid roles.");
                } else {
                    this.role = roles.get(0);
                }
            }
        } else {
            AlertUI.infoAlert("Email/Password fields can't be empty", "Login Error");
        }
    }

    public void getUiForRole() {
        switch (this.role.getDescription()) {
            case Constants.ROLE_ADMIN:

                try {
                    AdminUI adminUI = (AdminUI) this.mainApp.replaceSceneContent("/fxml/roles/Admin.fxml");
                    adminUI.setLoginUI(this);
                    adminUI.setMainApp(this.mainApp);
                } catch (Exception e) {
                    AlertUI.infoAlert(e.getMessage(), "Error");
                }

                break;
            case Constants.ROLE_USER:
                try {
                    UserUI userUI = (UserUI) this.mainApp.replaceSceneContent("/fxml/roles/User.fxml");
                    userUI.setLoginUI(this);
                    userUI.setMainApp(this.mainApp);
                } catch (Exception e) {
                    AlertUI.infoAlert(e.getMessage(), "Error");
                }
                break;
            default:
                AlertUI.infoAlert("Email and/or Password are incorrect, please try again", "Login Error");
        }
    }

    @FXML
    private void doLoginButton(ActionEvent actionEvent) {
        doLogin(actionEvent);
        getUiForRole();
    }

    public void enterMouseOver(MouseEvent action) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.valueOf("#e4491e"));
        shadow.setWidth(60);
        shadow.setHeight(50);
        shadow.setBlurType(BlurType.THREE_PASS_BOX);

        this.btnDoLogin.setMinWidth(151);
        this.btnDoLogin.setLayoutX(312);
        this.btnDoLogin.setStyle("-fx-background-color: #FF7B00" + " ; -fx-background-radius: 30" + " ; -fx-background-position: center");
        this.btnDoLogin.setAlignment(Pos.CENTER);
        this.btnDoLogin.setCursor(Cursor.HAND);
        this.btnDoLogin.setEffect(shadow);
    }

    public void enterMouseExit(MouseEvent action) {
        this.btnDoLogin.setMinWidth(114);
        this.btnDoLogin.setStyle("-fx-background-color:" + "#F78C28" + " ; -fx-background-radius:" + 30);
        this.btnDoLogin.setAlignment(Pos.CENTER);
        this.btnDoLogin.setLayoutX(332);
        this.btnDoLogin.setEffect(null);
    }
}
