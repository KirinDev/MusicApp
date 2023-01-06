package app.audioplayer;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioPlayer implements LineListener {

    boolean isPlaybackCompleted;

    public AudioPlayer() {}

    @Override
    public void update(LineEvent event) {
        if (LineEvent.Type.START == event.getType()) {
            System.out.println("Playback started.");
        } else if (LineEvent.Type.STOP == event.getType()) {
            isPlaybackCompleted = true;
            System.out.println("Playback completed.");
        }
    }

    public void playAudio( String path ) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if ( inputStream != null ) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

                Clip audioClip = AudioSystem.getClip();
                audioClip.addLineListener(this);
                audioClip.open(audioStream);

                FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(0.2) / Math.log(5.0) * 10.0);
                gainControl.setValue(dB);

                audioClip.setFramePosition(0);
                audioClip.start();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
