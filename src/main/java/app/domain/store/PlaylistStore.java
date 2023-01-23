package app.domain.store;

import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.shared.Constants;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void addMusicToPlaylist(Playlist playlist, Music music) {
        playlist.addMusic(music);
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

    public Set<Playlist> getPlaylists() {
        return this.store;
    }

    public Playlist getByName(String name) {
        for( Playlist i : this.store ) {
            if( i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public void insertToDatabase(Connection conn, String name) {
        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO Global_Playlist (name) VALUES (?)");
            stat.setString(1, name);
            stat.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
