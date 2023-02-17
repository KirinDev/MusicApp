package app.audioplayer;

import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class AudioPlayer implements LineListener {

    boolean isPlaybackCompleted;
    private Clip audioPlayer;
    private long pauseTime;
    private float volume;

    public AudioPlayer() {
        this.isPlaybackCompleted = true;
        this.pauseTime = 0;
    }

    @Override
    public void update(LineEvent event) {
        if (LineEvent.Type.START == event.getType()) {
            this.isPlaybackCompleted = false;
        } else if (LineEvent.Type.STOP == event.getType()) {
            this.isPlaybackCompleted = true;
        }
    }

    public void openAudio(String name) {
        /*
        StringBuilder fname = new StringBuilder();
        String[] arr = name.trim().toLowerCase(Locale.ROOT).split(" ");
        for (String s : arr) {
            fname.append(s);
        }
        */

        String music = String.format("music/%s", name);

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(music);
            if ( inputStream != null ) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

                this.audioPlayer = AudioSystem.getClip();
                this.audioPlayer.addLineListener(this);
                this.audioPlayer.open(audioStream);

                this.audioPlayer.setFramePosition(0);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playAudio(long time) {
        this.audioPlayer.setMicrosecondPosition(time);
        this.audioPlayer.start();
    }

    public long pauseAudio() {
        long pauseTime = this.audioPlayer.getMicrosecondPosition();
        this.audioPlayer.stop();
        return pauseTime;
    }

    public void playAudio2() {
        this.audioPlayer.setMicrosecondPosition(this.pauseTime);
        this.audioPlayer.start();
    }

    public void pauseAudio2() {
        this.pauseTime = this.audioPlayer.getMicrosecondPosition();
        this.audioPlayer.stop();
    }

    public void stopAudio() {
        this.audioPlayer.stop();
        this.audioPlayer.close();
        this.pauseTime = 0;
    }

    public long getAudioPosition() {
        return this.audioPlayer.getMicrosecondPosition();
    }

    public long getDuration() {
        return this.audioPlayer.getMicrosecondLength();
    }

    public boolean isRunning() {
        return this.isPlaybackCompleted;
    }

    public void setPlaybackCompleted() {
        this.isPlaybackCompleted = false;
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) this.audioPlayer.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        return (this.volume - gainControl.getMinimum()) / range ;
    }

    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) this.audioPlayer.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
        this.volume = gain;
    }
}
