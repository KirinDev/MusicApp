package app.ui.gui;

import app.controller.MusicPlayerController;
import app.mappers.MusicMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MyPlaylistsUI implements Initializable {

    private AppGUI mainApp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainApp(AppGUI mainApp) {
        this.mainApp = mainApp;
    }

}
