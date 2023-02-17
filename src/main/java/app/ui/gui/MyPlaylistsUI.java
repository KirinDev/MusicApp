package app.ui.gui;

import app.auth.AuthFacade;
import app.controller.App;
import app.controller.AuthController;
import app.controller.MusicPlayerController;
import app.domain.model.Playlist;
import app.domain.model.User;
import app.domain.store.UserStore;
import app.mappers.MusicMapper;
import app.ui.gui.roles.UserUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MyPlaylistsUI implements Initializable {

    private App app;
    private AppGUI mainApp;
    private AuthFacade auth;
    private User user;
    private Set<Playlist> myPlaylists = new HashSet<>();
    private String currentPlaylist;

    @FXML
    private Button search;

    @FXML
    private Button playlists;

    @FXML
    private Button myPlaylist;

    @FXML
    private Label userEmail = new Label();

    @FXML
    private ListView<String> playlistsView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.app = App.getInstance();
        this.auth = this.app.getKirinDev().getAuthFacade();

        playlistsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentPlaylist = playlistsView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void setMainApp(AppGUI mainApp) {
        this.mainApp = mainApp;
    }

    public void setEmailLabel(String email) {
        StringProperty str = new SimpleStringProperty(email);
        this.userEmail.textProperty().bind(str);
    }

    public void addPlaylists() {
        UserStore store = this.auth.getUserStore();
        this.user = store.getUserByID(this.userEmail.getText());
        this.myPlaylists = this.user.getPlaylists();
        this.playlistsView.getItems().addAll(this.user.getPlaylistsString());
    }

    @FXML
    public void goToSearch(ActionEvent actionEvent) {
        try {
            SearchUI searchUI = (SearchUI) this.mainApp.replaceSceneContent("/fxml/Search.fxml");
            searchUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    @FXML
    public void goToPlaylists(ActionEvent actionEvent) {
        try {
            UserUI userUI = (UserUI) this.mainApp.replaceSceneContent("/fxml/roles/User.fxml");
            userUI.setEmailLabel(this.userEmail.getText());
            userUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    @FXML
    public void goToMyPlaylist(ActionEvent actionEvent) {
        try {
            if( this.currentPlaylist != null) {
                Playlist playlist = this.user.getByName(this.currentPlaylist);
                PlaylistsUI playlistsUI = (PlaylistsUI) this.mainApp.replaceSceneContent("/fxml/Playlist.fxml");
                playlistsUI.setEmailLabel(this.userEmail.getText());
                playlistsUI.setPlaylist(playlist);
                playlistsUI.setPlaylistName(playlist.getName());
                playlistsUI.setMusics();
                playlistsUI.addPlaylists();
                playlistsUI.setMainApp(this.mainApp);
            }else{
                throw new NullPointerException("No selected item");
            }

        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

}
