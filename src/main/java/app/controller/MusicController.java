package app.controller;

import app.domain.model.Music;
import app.domain.store.MusicStore;

import java.sql.Connection;

public class MusicController {

    private App app;

    public MusicController() {
        this.app = App.getInstance();
    }

    public Music createMusic(String name, String file_name, String time, String artist) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.create(name, file_name, time, artist);
    }

    public boolean dataValidation(Music music) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.validation(music);
    }

    public boolean addMusic(Music music) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.add(music);
    }

    public void insertToDatabase(Connection conn, String name, String file_name, String time, String artist) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        store.insertToDatabase(conn, name, file_name, time, artist);
    }
}
