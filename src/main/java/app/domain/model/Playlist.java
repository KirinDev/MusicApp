package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Playlist {

    private String name;
    private Set<Music> musics;

    public Playlist(String name, Set<Music> musics) {
        if (!StringUtils.isBlank(name) ) {
            this.name = name;
            this.musics = musics;
        }else{
            throw new IllegalArgumentException("Playlist cannot have a name null/blank.");
        }
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

    public List<String> getMusicsString() {
        List<String> lst = new ArrayList<>();
        for (Music i : this.musics) {
            String str = i.toString();
            lst.add(str);
        }
        return lst;
    }
}
