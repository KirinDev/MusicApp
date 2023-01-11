package app.ui.console;

import app.controller.PlaylistController;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddToPlaylistUI implements Runnable {

    private PlaylistController ctrl;

    public AddToPlaylistUI() {
        ctrl = new PlaylistController();
    }

    public void run() {
        List<PlaylistDTO> playlistDTO = ctrl.getPlaylists();
        List<MusicDTO> musicsDTO = ctrl.getMusics();

        PlaylistDTO tempPlaylist = choosePlaylist(playlistDTO);
        MusicDTO tempMusic = chooseMusic(musicsDTO);

        Playlist playlist = ctrl.getPlaylistByName(tempPlaylist.getName());
        Music music = ctrl.getMusicByNameArtist(tempMusic.getName(), tempMusic.getArtist());

        ctrl.addMusicToPlaylist(playlist, music);

    }

    public PlaylistDTO choosePlaylist(List<PlaylistDTO> playlistDTO) {
        List<String> options = new ArrayList<>();
        for(PlaylistDTO i : playlistDTO) {
            options.add(i.getName());
        }
        int option = Utils.showAndSelectIndex(options, "\n\nChoose the PlayList: ");
        return playlistDTO.get(option);
    }

    public MusicDTO chooseMusic(List<MusicDTO> musicDTO) {
        List<String> options = new ArrayList<>();
        for(MusicDTO i : musicDTO) {
            options.add(i.getName());
        }
        int option = Utils.showAndSelectIndex(options, "\n\nChoose the Music: ");
        return musicDTO.get(option);
    }
}
