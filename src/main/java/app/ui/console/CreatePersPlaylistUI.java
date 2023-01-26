package app.ui.console;

import app.controller.PersonalPlaylistController;
import app.domain.model.Email;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.model.User;
import app.ui.console.utils.Utils;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class CreatePersPlaylistUI implements Runnable {

    private PersonalPlaylistController ctrl;

    public CreatePersPlaylistUI() {
        ctrl = new PersonalPlaylistController();
    }

    public void run() {
        System.out.println("| Creating new PlayList... |");
        String name = Utils.readLineFromConsole("Name: ");
        Set<Music> musics = new HashSet<>();

        Playlist list = ctrl.createPlaylist(name, musics);
        Email email = ctrl.getUserID();
        User user = ctrl.getUserByID(email);

        if( Utils.dataConfirmation(list).equals("yes")) {
            if(ctrl.addPlaylist(list, user)) {
                System.out.println("< The PlayList has been created successfully >");
                try {
                    Connection conn = ctrl.getServerConnection();
                    ctrl.insertToDatabase(conn, name, email.getEmail(), user);
                } catch (Exception e) {
                    System.err.println("« Error: Failed connect to SQL server »");
                }
            }else{
                System.err.println("« Error: Another existing PlayList already has this name. Please try again »");
            }
        }
    }
}
