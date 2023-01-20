package app.domain.store;

import app.domain.model.Music;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class MusicStore {

    private Set<Music> store = new HashSet<>();

    public MusicStore() { }

    public Music create(String name, String file_name, String time, String artist) {
        return new Music(name, file_name, time, artist);
    }

    public boolean add(Music music) {
        return music != null && !this.exists(music.getName(), music.getArtist()) && this.store.add(music);
    }

    public Optional<Music> getById(String name, String artist) {
        Iterator<Music> var2 = this.store.iterator();

        Music music;
        do {
            if (!var2.hasNext()) {
                return Optional.empty();
            }

            music = var2.next();
        } while(!music.hasNameArtist(name, artist));

        return Optional.of(music);
    }

    public boolean exists(String name, String artist) {
        Optional<Music> result = this.getById(name, artist);
        return result.isPresent();
    }

    public boolean validation(Music music) {
        return music.checkTimeFormat(music.getTime()) && music.fileExists(music.getFile_name());
    }

    public Music getByNameAndArtist(String name, String artist) {
        for(Music i : this.store) {
            if(i.getName().equals(name) && i.getArtist().equals(artist)) {
                return i;
            }
        }
        return null;
    }

    public Set<Music> getMusics() {
        return this.store;
    }

}
