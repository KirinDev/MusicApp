package app.ui.console;

import app.controller.PlayMusicController;
import app.ui.console.utils.Utils;

public class PlayMusicUI implements Runnable {

    private PlayMusicController ctrl;

    public PlayMusicUI() {
        ctrl = new PlayMusicController();
    }

    public void run() {
        boolean success = false;
        do {

            System.out.println("\n| Kirin Music Player |");
            String name = Utils.readLineFromConsole("Insert the name of the music: ");
            if (name != null) {
                success = true;
                String music = String.format("music/%s.wav", name);
                ctrl.play(music);
            }

        }while(!success);
    }
}
