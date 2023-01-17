package app.controller;

import app.domain.model.Email;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.model.User;
import app.domain.store.MusicStore;
import app.domain.store.PlaylistStore;
import app.domain.store.UserStore;
import app.mappers.MusicMapper;
import app.mappers.PlaylistMapper;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;

import java.util.List;
import java.util.Set;

public class PersonalPlaylistController {

    private App app;
    private PlaylistMapper plMapper;
    private MusicMapper muMapper;

    public PersonalPlaylistController() {
        this.app = App.getInstance();
        this.plMapper = new PlaylistMapper();
        this.muMapper = new MusicMapper();
    }

    public Email getUserID() {
        return this.app.getCurrentUserSession().getUserId();
    }

    public User getUserByID(Email email) {
        UserStore store = this.app.getKirinDev().getAuthFacade().getUserStore();
        return store.getUserByID(email);
    }

    public Playlist createPlaylist(String name, Set<Music> musics) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();
        return store.create(name, musics);
    }

    public boolean addPlaylist(Playlist playlist, User user ) {
        return user.addPlaylist(playlist);
    }

    public List<PlaylistDTO> getPlaylists(User user) {
        Set<Playlist> lst = user.getPlaylists();
        return this.plMapper.toDTO(lst);
    }

    public List<MusicDTO> getMusics() {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        Set<Music> lst = store.getMusics();
        return this.muMapper.toDTO(lst);
    }

    public Playlist getPlaylistByName(String name, User user) {
        return user.getByName(name);
    }

    public Music getMusicByNameArtist(String name, String artist) {
        MusicStore store = this.app.getKirinDev().getMusicStore();
        return store.getByNameAndArtist(name, artist);
    }

    public void addMusicToPlaylist(Playlist playlist, Music music, User user) {
        user.addMusicToPlaylist(playlist, music);
    }
}
