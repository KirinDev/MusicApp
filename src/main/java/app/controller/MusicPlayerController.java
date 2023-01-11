package app.controller;

import app.audioplayer.AudioPlayer;
import app.mappers.dto.PlaylistDTO;

public class MusicPlayerController {

    private App app;

    public MusicPlayerController() {
        this.app = App.getInstance();
    }

    public void open( String music ) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.openAudio(music);
    }

    public void openPlaylist(PlaylistDTO playlist) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.openPlaylist(playlist);
    }

    public void play( long time ) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.playAudio(time);
    }

    public void stop() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.stopAudio();
    }

    public long pause() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        return player.pauseAudio();
    }

    public long getTime() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        return player.getAudioPosition();
    }
}
