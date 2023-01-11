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

                FloatControl gainControl = (FloatControl) this.audioPlayer.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(0.2) / Math.log(5.0) * 10.0);
                gainControl.setValue(dB);

                this.audioPlayer.setFramePosition(0);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openPlaylist(PlaylistDTO playlist) {
        List<MusicDTO> musics = playlist.getMusics();
        for( MusicDTO i : musics ) {
            try {
                openAudio(i.getFile_name());
            }catch (Exception e) {
                e.printStackTrace();
            }
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

    public boolean isRunning() {
        return this.audioPlayer.isRunning();
    }
}
