package app.ui.console;

import app.controller.PersonalPlaylistController;
import app.domain.model.Email;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.model.User;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddToPersPlaylistUI implements Runnable {

    private PersonalPlaylistController ctrl;

    public AddToPersPlaylistUI() {
        ctrl = new PersonalPlaylistController();
    }

    public void run() {
        Email email = ctrl.getUserID();
        User user = ctrl.getUserByID(email);

        List<PlaylistDTO> playlistDTO = ctrl.getPlaylists(user);
        List<MusicDTO> musicsDTO = ctrl.getMusics();

        PlaylistDTO tempPlaylist = Utils.choosePlaylist(playlistDTO);
        MusicDTO tempMusic = Utils.chooseMusic(musicsDTO);

        Playlist playlist = ctrl.getPlaylistByName(tempPlaylist.getName());
        Music music = ctrl.getMusicByNameArtist(tempMusic.getName(), tempMusic.getArtist());

        ctrl.addMusicToPlaylist(playlist, music);

    }

}
