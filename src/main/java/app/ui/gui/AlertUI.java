package app.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertUI {

    public static Alert createAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        }

        return alert;
    }

    public static void infoAlert(String message, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Error!");
        alert.setHeaderText(headerText);
        alert.setContentText(message);

        alert.show();
    }

    public static void notificationAlert(String message, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Error!");
        alert.setHeaderText(headerText);
        alert.setContentText(message);

        alert.show();
    }
}
