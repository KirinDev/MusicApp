package app.domain.model;

import java.util.Set;

public class Playlist {

    private String name;
    private Set<Music> musics;

    public Playlist(String name, Set<Music> musics) {
        this.name = name;
        this.musics = musics;
    }

    public String getName() {
        return name;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public boolean hasID(String name) {
        return name.equals(this.name);
    }

    public void addMusic(Music music) {
        musics.add(music);
    }
}
