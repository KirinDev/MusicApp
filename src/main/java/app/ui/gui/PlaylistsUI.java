package app.ui.gui;

import app.controller.MusicPlayerController;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.mappers.MusicMapper;
import app.mappers.dto.MusicDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class PlaylistsUI implements Initializable {

    private AppGUI mainApp;
    private Playlist playlist;
    private List<MusicDTO> musics = new ArrayList<>();
    private MusicPlayerController ctrl;
    private MusicMapper mapper;

    private int currentSong;
    private boolean running;
    private Timer timer;
    private TimerTask task;

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

    public void play() {
        boolean isRunning = ctrl.checkIfRunning();
        if(!isRunning){
            float db = ctrl.getVolume();
            ctrl.changeVolume(db);
        }else{
            ctrl.open(this.musics.get(this.currentSong).getFile_name());
            float db = ctrl.getVolume();
            ctrl.changeVolume(db);
        }
        setMusicName(this.musics.get(this.currentSong).getName());
        beginTimer();
        ctrl.play2();
    }

    public void pause() {
        ctrl.pause2();
    }

    public void stop() {
        ctrl.stop();
    }

    public void next() {
        stop();
        if(this.currentSong < musics.size()) {
            if(running)
                cancelTimer();
            this.currentSong++;
        }else{
            if(running)
                cancelTimer();
            this.currentSong = 0;
        }
        play();
    }

    public void previous() {
        stop();
        if(this.currentSong > 0) {

            if(running)
                cancelTimer();

            this.currentSong--;
            play();
        }
    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                running = true;
                double current = ctrl.getTime();
                double end = ctrl.getDuration();

                musicProgress.setProgress(current/end);

                if(current/end == 1) {
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void cancelTimer() {
        this.running = false;
        this.timer.cancel();
    }

    public void goToMyPlaylists() {
        try {
            MyPlaylistsUI myPlaylistsUI = (MyPlaylistsUI) this.mainApp.replaceSceneContent("/fxml/MyPlaylist.fxml");
            myPlaylistsUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }

    public void goToSearch() {
        try {
            SearchUI searchUI = (SearchUI) this.mainApp.replaceSceneContent("/fxml/Search.fxml");
            searchUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            AlertUI.infoAlert(e.getMessage(), "Error");
        }
    }
}
