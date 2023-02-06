package app.ui.gui;

import app.domain.model.Music;
import app.domain.model.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistsUI implements Initializable {

    private AppGUI mainApp;
    private Playlist playlist;

    @FXML
    private ListView<String> musicsView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        musicsView.getItems().addAll(playlist.getMusicsString());
    }

    public void setMainApp(AppGUI mainApp) {
        this.mainApp = mainApp;
    }

}
