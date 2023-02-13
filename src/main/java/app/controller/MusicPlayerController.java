package app.controller;

import app.audioplayer.AudioPlayer;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;

import java.util.List;

public class MusicPlayerController {

    private App app;

    public MusicPlayerController() {
        this.app = App.getInstance();
    }

    public void open( String music ) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.openAudio(music);
    }

    public void changeVolume(float db) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.setVolume(db);
    }

    public float getVolume() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        return player.getVolume();
    }

    public void play( long time ) {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.playAudio(time);
    }

    public void play2() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.playAudio2();
    }

    public void pause2() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.pauseAudio2();
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

    public long getDuration() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        return player.getDuration();
    }

    public boolean checkIfRunning() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        return player.isRunning();
    }

    public void setPlayback() {
        AudioPlayer player = this.app.getKirinDev().getPlayer();
        player.setPlaybackCompleted();
    }
}
