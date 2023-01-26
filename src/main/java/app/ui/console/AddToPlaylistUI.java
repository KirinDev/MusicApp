package app.ui.console;

import app.controller.GlobalPlaylistController;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;
import app.ui.console.utils.Utils;

import java.sql.Connection;
import java.util.List;

public class AddToPlaylistUI implements Runnable {

    private GlobalPlaylistController ctrl;

    public AddToPlaylistUI() {
        ctrl = new GlobalPlaylistController();
    }

    public void run() {

        List<PlaylistDTO> playlistDTO = ctrl.getPlaylists();
        List<MusicDTO> musicsDTO = ctrl.getMusics();

        PlaylistDTO tempPlaylist = Utils.choosePlaylist(playlistDTO);
        MusicDTO tempMusic = Utils.chooseMusic(musicsDTO);

        Playlist playlist = ctrl.getPlaylistByName(tempPlaylist.getName());
        Music music = ctrl.getMusicByNameArtist(tempMusic.getName(), tempMusic.getArtist());

        ctrl.addMusicToPlaylist(playlist, music);

        try {
            Connection conn = ctrl.getServerConnection();
            ctrl.insertToDatabase(conn, playlist.getName(), music.getFile_name());
        } catch (Exception e) {
            System.err.println("« Error: Failed connect to SQL server »");
        }

    }
}
