package app.ui.console;

import app.controller.MusicController;
import app.domain.model.Music;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CreateMusicUI implements Runnable {

    private MusicController ctrl;

    public CreateMusicUI() {
        ctrl = new MusicController();
    }

    public void run() {
        System.out.println("| Song Registration |");
        String name = Utils.readLineFromConsole("Title: ");
        String file_name = Utils.readLineFromConsole("File: ");
        String time = Utils.readLineFromConsole("Time: ");
        String artist = Utils.readLineFromConsole("Artist: ");

        Music music = ctrl.createMusic(name, file_name, time, artist);

        if( dataConfirmation(music).equals("yes")) {
            if( ctrl.dataValidation(music)) {
                if(ctrl.addMusic(music)) {
                    System.out.println("< The music has been registered in the system successfully >");

                }else{
                    System.err.println("« Error: The music introduced already exists in the system »");
                }
            }else{
                System.err.println("« Error: The music data introduced is invalid. Try again »");
            }
        }
    }

    public String dataConfirmation(Music music) {
        List<String> options = new ArrayList<>();
        options.add("yes");
        options.add("no");

        System.out.println("=========Music Registration Confirmation========");
        System.out.println("Title: " + music.getName());
        System.out.println("File: " + music.getFile_name());
        System.out.println("Time: " + music.getTime());
        System.out.println("Artist: " + music.getArtist());
        System.out.println("=======================//======================");

        int option = Utils.showAndSelectIndex(options, "\n\nConfirm data:");
        return options.get(option);
    }
}
