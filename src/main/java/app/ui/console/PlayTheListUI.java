package app.ui.console;

import app.controller.MusicPlayerController;
import app.controller.PlaylistController;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayTheListUI implements Runnable {

    private PlaylistController ctrl;
    private MusicPlayerController ctrlPlayer;

    public PlayTheListUI() {
        ctrl = new PlaylistController();
        ctrlPlayer = new MusicPlayerController();
    }

    public void run() {
        List<PlaylistDTO> listDTO = ctrl.getPlaylists();
        PlaylistDTO playlist = choosePlaylist(listDTO);
        List<MusicDTO> musics = playlist.getMusics();

        System.out.println("\n| PlayList: " + playlist.getName() + " |");
        for(MusicDTO i : musics) {
            ctrlPlayer.open(i.getFile_name());
            ctrlPlayer.play(0);
            System.out.println("\n< Currently playing: " + i.getName());
            System.out.println("< Time of music: " + i.getTime());

            ctrlPlayer.setPlayback();
            boolean isPaused = false;
            long pauseTime = 0;

            while (ctrlPlayer.checkIfRunning() || isPaused) {
                String option = musicAction();
                if( option.equals("resume")) {
                    ctrlPlayer.play(pauseTime);
                    isPaused = false;
                }else if(option.equals("pause")) {
                    isPaused = true;
                    pauseTime = ctrlPlayer.pause();
                }else{
                    System.out.println("\n< Skipping to the next music... >");
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            ctrlPlayer.stop();
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

    public String musicAction() {
        List<String> options = new ArrayList<>();
        options.add("resume");
        options.add("pause");
        options.add("next music");

        int option;
        option = Utils.showAndSelectIndex(options, "\n\nMusic Menu:");
        return options.get(option);
    }
}
