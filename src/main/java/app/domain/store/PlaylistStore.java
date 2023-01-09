package app.domain.store;

import app.domain.model.Music;
import app.domain.model.Playlist;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class PlaylistStore {

    private Set<Playlist> store = new HashSet<>();

    public PlaylistStore() { }

    public Playlist create(String name, Set<Music> musics) {
        return new Playlist(name, musics);
    }

    public boolean add(Playlist playlist) {
        return playlist != null && !this.exists(playlist.getName()) && this.store.add(playlist);
    }

    public Optional<Playlist> getById(String name) {
        Iterator<Playlist> var2 = this.store.iterator();

        Playlist playlist;
        do {
            if (!var2.hasNext()) {
                return Optional.empty();
            }

            playlist = var2.next();
        } while(!playlist.hasID(name));

        return Optional.of(playlist);
    }

    public boolean exists(String name) {
        Optional<Playlist> result = this.getById(name);
        return result.isPresent();
    }

}
