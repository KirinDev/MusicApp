package app.controller;

import app.audioplayer.AudioPlayer;

public class PlayMusicController {

    private App app;

    public PlayMusicController() {
        this.app = App.getInstance();
    }

    public void play( String music ) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.playAudio(music);
    }
}
