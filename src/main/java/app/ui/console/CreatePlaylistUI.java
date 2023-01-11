package app.ui.console;

import app.controller.PlaylistController;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreatePlaylistUI implements Runnable {

    private PlaylistController ctrl;

    public CreatePlaylistUI() {
        ctrl = new PlaylistController();
    }

    public void run() {
        System.out.println("| Creating new PlayList... |");
        String name = Utils.readLineFromConsole("Name: ");
        Set<Music> musics = new HashSet<>();

        Playlist list = ctrl.createPlaylist(name, musics);

        if( dataConfirmation(list).equals("yes")) {
            if(ctrl.addPlaylist(list)) {
                System.out.println("< The PlayList has been created successfully >");
            }else{
                System.err.println("« Error: Another existing PlayList already has this name. Please try again »");
            }
        }
    }

    public String dataConfirmation(Playlist playlist) {
        List<String> options = new ArrayList<>();
        options.add("yes");
        options.add("no");

        System.out.println("=========PlayList Confirmation========");
        System.out.println("Name: " + playlist.getName());
        System.out.println("=================//=================");

        int option = Utils.showAndSelectIndex(options, "\n\nUser Menu:");
        return options.get(option);
    }
}
