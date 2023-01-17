package app.domain.store;

import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.shared.Constants;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class PlaylistStore {

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    private Set<Playlist> store = new HashSet<>();

    public PlaylistStore() { }

    public Playlist create(String name, Set<Music> musics) {
        return new Playlist(name, musics);
    }

    public boolean add(Playlist playlist) {
        if(playlist != null && !this.exists(playlist.getName()) && this.store.add(playlist)) {
            saveList();
            return true;
        }else{
            return false;
        }
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

    public void saveList() {
        try {
            this.outFile = new FileOutputStream(Constants.PLAYLIST_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(this.store);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadToLocalList() {
        try {
            this.inFile = new FileInputStream(Constants.PLAYLIST_FILE);
            this.input = new ObjectInputStream(inFile);
            this.store = (Set<Playlist>) input.readObject();
            this.input.close();
            this.inFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
