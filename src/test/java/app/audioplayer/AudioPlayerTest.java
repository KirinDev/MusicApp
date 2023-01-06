package app.audioplayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudioPlayerTest {

    AudioPlayer player = new AudioPlayer();

    @Test
    void playAudio() {
        String file = "music/AfterDark.wav";
        player.playAudio(file);
    }
}