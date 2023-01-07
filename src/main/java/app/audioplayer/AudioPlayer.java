package app.audioplayer;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioPlayer implements LineListener {

    boolean isPlaybackCompleted;
    private Clip audioPlayer;

    public AudioPlayer() { }

    @Override
    public void update(LineEvent event) {
        if (LineEvent.Type.START == event.getType()) {
            System.out.println("Playback started.");
        } else if (LineEvent.Type.STOP == event.getType()) {
            isPlaybackCompleted = true;
            System.out.println("Playback completed.");
        }
    }

    public void openAudio(String path) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if ( inputStream != null ) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

                this.audioPlayer = AudioSystem.getClip();
                this.audioPlayer.addLineListener(this);
                this.audioPlayer.open(audioStream);

                FloatControl gainControl = (FloatControl) this.audioPlayer.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(0.2) / Math.log(5.0) * 10.0);
                gainControl.setValue(dB);

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

    public void stopAudio() {
        this.audioPlayer.stop();
        this.audioPlayer.close();
    }

    public long getAudioPosition() {
        return this.audioPlayer.getMicrosecondPosition();
    }
}
