package app.ui.console;

import app.controller.MusicPlayerController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicOptionsUI implements Runnable {

    private final MusicPlayerController ctrl;

    public MusicOptionsUI() {
        ctrl = new MusicPlayerController();
    }

    public void run() {

        boolean success = false;
        do {

            System.out.println("\n| Kirin Music Player |");
            String name = Utils.readLineFromConsole("Insert the name of the music: ");
            if (name != null) {
                success = true;
                StringBuilder fname = new StringBuilder();
                String[] arr = name.trim().toLowerCase(Locale.ROOT).split(" ");
                for (String s : arr) {
                    fname.append(s);
                }
                String music = String.format("music/%s.wav", fname);
                ctrl.open(music);
            }

        } while (!success);

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Play " , new MusicManagerUI("play")));
        options.add(new MenuItem("Pause " , new MusicManagerUI("pause")));
        options.add(new MenuItem("Stop " , new MusicManagerUI("stop")));

        int option = 0;
        do {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nUser Menu:");
                if ( (option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            } catch (Exception e){System.out.print("Invalid option! Try again");}
        }
        while (option != -1 );
    }
}
