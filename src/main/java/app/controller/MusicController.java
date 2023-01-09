package app.controller;

import app.domain.model.Music;
import app.domain.store.MusicStore;

public class MusicController {

    private App app;

    public MusicController() {
        this.app = App.getInstance();
    }

    public Music createMusic(String name, String file_name, String time, String artist) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.create(name, file_name, time, artist);
    }

    public boolean addMusic(Music music) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.add(music);
    }
}
