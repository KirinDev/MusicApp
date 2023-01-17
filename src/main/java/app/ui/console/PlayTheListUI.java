package app.ui.console;

import app.controller.MusicPlayerController;
import app.controller.GlobalPlaylistController;
import app.controller.PersonalPlaylistController;
import app.domain.model.Email;
import app.domain.model.User;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PlayTheListUI implements Runnable {

    private GlobalPlaylistController ctrlGlobal;
    private PersonalPlaylistController ctrlPers;
    private MusicPlayerController ctrlPlayer;
    private String option;

    public PlayTheListUI(String option) {
        ctrlGlobal = new GlobalPlaylistController();
        ctrlPers = new PersonalPlaylistController();
        ctrlPlayer = new MusicPlayerController();
        this.option = option;
    }

    public void run() {
        List<PlaylistDTO> listDTO;
        PlaylistDTO playlist;
        List<MusicDTO> musics;

        if(option.equals("global")) {
            listDTO = ctrlGlobal.getPlaylists();
        }else{
            Email email = ctrlPers.getUserID();
            User user = ctrlPers.getUserByID(email);
            listDTO = ctrlPers.getPlaylists(user);
        }

        playlist = Utils.choosePlaylist(listDTO);
        musics = playlist.getMusics();

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
                    System.out.println("\n< The music has been resumed >");
                    ctrlPlayer.play(pauseTime);
                    isPaused = false;
                }else if(option.equals("pause")) {
                    System.out.println("\n< The music has been paused >");
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
