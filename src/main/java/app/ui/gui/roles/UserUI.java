package app.ui.gui.roles;

import app.controller.App;
import app.controller.GlobalPlaylistController;
import app.domain.store.PlaylistStore;
import app.ui.gui.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UserUI implements Initializable {

    private AppGUI mainAppGUI;
    private LoginUI loginUI;
    private App app;
    private GlobalPlaylistController ctrl;

    @FXML
    private Label userEmail = new Label();
    @FXML
    private Button myPlaylists;
    @FXML
    private Button search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ctrl = new GlobalPlaylistController();
    }

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

    public void animeMouseClicked(MouseEvent action) {
        try {
            PlaylistsUI playlistsUI = (PlaylistsUI) this.mainAppGUI.replaceSceneContent("/fxml/Playlist.fxml");
            playlistsUI.setPlaylist(ctrl.getPlaylistByName("Anime"));
            playlistsUI.setMusics();
            playlistsUI.setEmailLabel(this.userEmail.getText());
            playlistsUI.setPlaylistName("Anime");
            playlistsUI.addPlaylists();
            playlistsUI.setMainApp(this.mainAppGUI);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void goToMyPlaylists() {
        try {
            MyPlaylistsUI myPlaylistsUI = (MyPlaylistsUI) this.mainAppGUI.replaceSceneContent("/fxml/MyPlaylists.fxml");
            myPlaylistsUI.setEmailLabel(this.userEmail.getText());
            myPlaylistsUI.addPlaylists();
            myPlaylistsUI.setMainApp(this.mainAppGUI);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void goToSearch() {
        try {
            SearchUI searchUI = (SearchUI) this.mainAppGUI.replaceSceneContent("/fxml/Search.fxml");
            searchUI.setMainApp(this.mainAppGUI);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }
}
