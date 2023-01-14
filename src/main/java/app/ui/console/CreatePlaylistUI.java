package app.ui.console;

import app.controller.GlobalPlaylistController;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreatePlaylistUI implements Runnable {

    private GlobalPlaylistController ctrl;

    public CreatePlaylistUI() {
        ctrl = new GlobalPlaylistController();
    }

    public void run() {
        System.out.println("| Creating new PlayList... |");
        String name = Utils.readLineFromConsole("Name: ");
        Set<Music> musics = new HashSet<>();

        Playlist list = ctrl.createPlaylist(name, musics);

        if( Utils.dataConfirmation(list).equals("yes")) {
            if(ctrl.addPlaylist(list)) {
                System.out.println("< The PlayList has been created successfully >");
            }else{
                System.err.println("« Error: Another existing PlayList already has this name. Please try again »");
            }
        }
    }


}
