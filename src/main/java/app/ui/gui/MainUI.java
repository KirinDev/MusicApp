package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUI implements Initializable {

    @FXML
    public Button loginButton;
    public Button registerButton;

    private App mainApp;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    public App getMainApp() {
        return this.mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            LoginUI loginUI = (LoginUI) this.mainApp.replaceSceneContent("/fxml/Login.fxml");
            loginUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    @FXML
    public void register(ActionEvent actionEvent) {
        try {
            RegisterUI registerUI = (RegisterUI) this.mainApp.replaceSceneContent("/fxml/Register.fxml");
            registerUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void loginMouseOver(MouseEvent action) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.valueOf("#e4491e"));
        shadow.setWidth(60);
        shadow.setHeight(50);
        shadow.setBlurType(BlurType.THREE_PASS_BOX);

        this.loginButton.setMinWidth(330);
        this.loginButton.setLayoutX(282);
        this.loginButton.setStyle("-fx-background-color: #FFB448" + " ; -fx-background-radius: 30" + " ; -fx-background-position: center");
        this.loginButton.setAlignment(Pos.CENTER);
        this.loginButton.setCursor(Cursor.HAND);
        this.loginButton.setEffect(shadow);
    }

    public void registerMouseOver(MouseEvent action) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.valueOf("#e4491e"));
        shadow.setWidth(60);
        shadow.setHeight(50);
        shadow.setBlurType(BlurType.THREE_PASS_BOX);

        this.registerButton.setMinWidth(330);
        this.registerButton.setLayoutX(282);
        this.registerButton.setStyle("-fx-background-color:" + "#FFB448" + " ; -fx-background-radius:" + 30);
        this.registerButton.setAlignment(Pos.CENTER);
        this.registerButton.setCursor(Cursor.HAND);
        this.registerButton.setEffect(shadow);
    }

    public void loginMouseExit(MouseEvent action) {
        this.loginButton.setMinWidth(310);
        this.loginButton.setStyle("-fx-background-color:" + "#D9EEE1" + " ; -fx-background-radius:" + 30);
        this.loginButton.setAlignment(Pos.CENTER);
        this.loginButton.setLayoutX(292);
        this.loginButton.setEffect(null);
    }

    public void registerMouseExit(MouseEvent action) {
        this.registerButton.setMinWidth(310);
        this.registerButton.setStyle("-fx-background-color:" + "#D9EEE1" + " ; -fx-background-radius:" + 30);
        this.registerButton.setAlignment(Pos.CENTER);
        this.registerButton.setLayoutX(292);
        this.registerButton.setEffect(null);
    }
}
