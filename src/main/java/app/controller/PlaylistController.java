package app.controller;

import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.store.PlaylistStore;

import java.util.Set;

public class PlaylistController {

    private App app;

    public PlaylistController() {
        this.app = App.getInstance();
    }

    public Playlist createPlaylist(String name, Set<Music> musics) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        return store.create(name, musics);
    }

    public boolean addPlaylist(Playlist playlist) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        return store.add(playlist);
    }
}
