package app.ui.gui;

import app.domain.model.Playlist;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistsUI implements Initializable {

    private AppGUI mainApp;
    private Playlist playlist;

    @FXML
    private ListView<String> musicsView;

    @FXML
    private Label userEmail = new Label();

    @FXML
    private Label playlistName = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setMainApp(AppGUI mainApp) {
        this.mainApp = mainApp;
    }

    public void setEmailLabel(String email) {
        StringProperty str = new SimpleStringProperty(email);
        this.userEmail.textProperty().bind(str);
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void setPlaylistName(String name) {
        StringProperty str = new SimpleStringProperty(name);
        this.playlistName.textProperty().bind(str);
    }

    public void addPlaylists() {
        this.musicsView.getItems().addAll(playlist.getMusicsString());
    }

}
