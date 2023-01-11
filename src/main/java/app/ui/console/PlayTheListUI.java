package app.ui.console;

import app.controller.MusicPlayerController;
import app.controller.PlaylistController;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PlayTheListUI implements Runnable {

    private PlaylistController ctrl;
    private MusicPlayerController ctrlPlayer;

    public PlayTheListUI() {
        ctrl = new PlaylistController();
        ctrlPlayer = new MusicPlayerController();
    }

    public void run() {
        System.out.println("fuck");
        List<PlaylistDTO> listDTO = ctrl.getPlaylists();
        PlaylistDTO playlist = choosePlaylist(listDTO);
        List<MusicDTO> musics = playlist.getMusics();

        try {
            for(MusicDTO i : musics) {
                System.out.println(i.getFile_name());
                ctrlPlayer.open(i.getFile_name());
                ctrlPlayer.play(0);
            }
        }catch(Exception e){
            System.out.println("gay");
        }

    }

    public PlaylistDTO choosePlaylist(List<PlaylistDTO> playlistDTO) {
        List<String> options = new ArrayList<>();
        for(PlaylistDTO i : playlistDTO) {
            options.add(i.getName());
        }
        int option = Utils.showAndSelectIndex(options, "\n\nChoose the PlayList: ");
        return playlistDTO.get(option);
    }
}
