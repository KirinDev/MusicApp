package app.ui.gui;

import app.controller.MusicPlayerController;
import app.domain.model.Playlist;
import app.mappers.MusicMapper;
import app.mappers.dto.MusicDTO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class PlaylistsUI implements Initializable {

    private AppGUI mainApp;
    private Playlist playlist;
    private List<MusicDTO> musics = new ArrayList<>();
    private MusicPlayerController ctrl;
    private MusicMapper mapper;

    private int currentSong;
    private String currentSelSong;
    private String song;
    private boolean running;
    private Timeline timeline;

    @FXML
    private Button myPlaylists;

    @FXML
    private Button search;

    @FXML
    private ListView<String> musicsView;

    @FXML
    private Label userEmail = new Label();

    @FXML
    private Label playlistName = new Label();

    @FXML
    private Label songName = new Label();

    @FXML
    private Slider volume;

    @FXML
    private ProgressBar musicProgress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ctrl = new MusicPlayerController();
        mapper = new MusicMapper();
        this.currentSong = 0;

        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ctrl.changeVolume((float) volume.getValue());
            }
        });

        musicsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentSelSong = musicsView.getSelectionModel().getSelectedItem();
            }
        });

        musicProgress.setStyle("-fx-accent:#0E1920");
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

    public void setMusics() {
        this.musics = mapper.toDTO(this.playlist.getMusics());
    }

    public void setPlaylistName(String name) {
        StringProperty str = new SimpleStringProperty(name);
        this.playlistName.textProperty().bind(str);
    }

    public void setMusicName(String name) {
        StringProperty str = new SimpleStringProperty(name);
        this.songName.textProperty().bind(str);
    }

    public void addPlaylists() {
        this.musicsView.getItems().addAll(playlist.getMusicsString());
    }

    public int getIndexByName() {
        String[] arr = this.currentSelSong.split(",");
        String[] arr2 = arr[0].split(":");
        this.song = arr2[1];
        int index = 0;
        for(int i = 0 ; i < this.musics.size() ; i++) {
            if(musics.get(i).getName().equals(this.song))
                index = i;
        }
        return index;
    }

    public void play() {
        boolean isCompleted = ctrl.checkIfRunning();

        if(this.currentSelSong != null) {
            playSong();
        }else{
            if(isCompleted){
                ctrl.open(this.musics.get(this.currentSong).getFile_name());
                float db = ctrl.getVolume();
                ctrl.changeVolume(db);
                setMusicName(this.musics.get(this.currentSong).getName());
                beginTimer();
                ctrl.play2();
            }
        }
    }

    public void playSong() {
        boolean isCompleted = ctrl.checkIfRunning();

        if(!isCompleted){
            stop();
        }
        this.currentSong = getIndexByName();
        ctrl.open(this.musics.get(this.currentSong).getFile_name());
        float db = ctrl.getVolume();
        ctrl.changeVolume(db);
        setMusicName(this.musics.get(this.currentSong).getName());
        beginTimer();
        ctrl.play2();

        this.currentSelSong = null;
        this.musicsView.getSelectionModel().clearSelection();
    }

    public void playNextOrPreviousSong() {
        boolean isCompleted = ctrl.checkIfRunning();

        if(isCompleted){
            ctrl.open(this.musics.get(this.currentSong).getFile_name());
            float db = ctrl.getVolume();
            ctrl.changeVolume(db);
            beginTimer();
            ctrl.play2();
            setMusicName(this.musics.get(this.currentSong).getName());
        }
    }

    public void pause() {
        ctrl.pause2();
    }

    public void stop() {
        ctrl.stop();
    }

    public void next() {

        if(!ctrl.checkIfRunning())
            stop();

        if(this.currentSong < musics.size() - 1) {
            if(running)
                cancelTimer();
            this.currentSong++;
        }else{
            if(running)
                cancelTimer();
            this.currentSong = 0;
        }
        playNextOrPreviousSong();
    }

    public void next2() {
        if(!ctrl.checkIfRunning()) {
            stop();
            cancelTimer();
        }

        if(this.currentSong < musics.size() - 1) {
            this.currentSong++;
        }else{
            this.currentSong = 0;
        }
        playNextOrPreviousSong();
    }

    public void previous() {
        stop();
        if(this.currentSong > 0) {

            if(running)
                cancelTimer();

            this.currentSong--;
            playNextOrPreviousSong();
        }
    }

    public void beginTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                running = true;
                double current = ctrl.getTime();
                double end = ctrl.getDuration();
                musicProgress.setProgress(current/end);

                if(current/end == 1) {
                    cancelTimer();
                    next2();
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void cancelTimer() {
        this.running = false;
        this.timeline.stop();
    }

    @FXML
    public void goToMyPlaylists(ActionEvent actionEvent) {
        try {
            MyPlaylistsUI myPlaylistsUI = (MyPlaylistsUI) this.mainApp.replaceSceneContent("/fxml/MyPlaylists.fxml");
            myPlaylistsUI.setEmailLabel(this.userEmail.getText());
            myPlaylistsUI.addPlaylists();
            myPlaylistsUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
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
}
