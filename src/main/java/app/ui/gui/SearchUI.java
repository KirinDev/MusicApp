package app.ui.gui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchUI implements Initializable {

    private AppGUI mainApp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setMainApp(AppGUI mainApp) {
        this.mainApp = mainApp;
    }

}
