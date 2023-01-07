package app.ui.console;

import app.controller.MusicManagerController;

public class MusicManagerUI implements Runnable {

    private final MusicManagerController ctrl;
    private String option;

    public MusicManagerUI(String option) {
        ctrl = new MusicManagerController();
        this.option = option;
    }

    public void run() {

        long time = ctrl.getTime();

        switch (option) {
            case "play" -> {
                System.out.println(time);
                ctrl.play(time);
                System.out.println("\n| Kirin Music Player |");
                System.out.println("< Now Playing: >");
            }
            case "pause" -> {
                long pauseTime = ctrl.pause();
                System.out.println("\n| Kirin Music Player |");
                System.out.println("< Music has been paused >");
                System.out.println("< Time at pause: " + pauseTime);
            }
            case "stop" -> {
                ctrl.stop();
                System.out.println("\n| Kirin Music Player |");
                System.out.println("\n< The music has been stopped >");
            }
        }

    }
}
