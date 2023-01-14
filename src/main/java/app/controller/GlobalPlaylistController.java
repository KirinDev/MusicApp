package app.controller;

import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.store.MusicStore;
import app.domain.store.PlaylistStore;
import app.mappers.MusicMapper;
import app.mappers.PlaylistMapper;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;

import java.util.List;
import java.util.Set;

public class GlobalPlaylistController {

    private App app;
    private PlaylistMapper plMapper;
    private MusicMapper muMapper;

    public GlobalPlaylistController() {
        this.app = App.getInstance();
        this.plMapper = new PlaylistMapper();
        this.muMapper = new MusicMapper();
    }

    public Playlist createPlaylist(String name, Set<Music> musics) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        return store.create(name, musics);
    }

    public boolean addPlaylist(Playlist playlist) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        return store.add(playlist);
    }

    public List<PlaylistDTO> getPlaylists() {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        Set<Playlist> lst = store.getPlaylists();
        return this.plMapper.toDTO(lst);
    }

    public List<MusicDTO> getMusics() {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        Set<Music> lst = store.getMusics();
        return this.muMapper.toDTO(lst);
    }

    public Playlist getPlaylistByName(String name) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        return store.getByName(name);
    }

    public Music getMusicByNameArtist(String name, String artist) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.getByNameAndArtist(name, artist);
    }

    public void addMusicToPlaylist(Playlist playlist, Music music) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        store.addMusicToPlaylist(playlist, music);
    }
}
