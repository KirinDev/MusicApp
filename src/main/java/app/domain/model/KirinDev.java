package app.domain.model;

import app.audioplayer.AudioPlayer;
import app.auth.AuthFacade;
import app.domain.store.PlaylistStore;
import org.apache.commons.lang3.StringUtils;

public class KirinDev {

    private String designation;
    private AuthFacade authFacade;
    private PlaylistStore playlistStore;
    private AudioPlayer player;

    public KirinDev(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

        this.playlistStore = new PlaylistStore();
        this.player = new AudioPlayer();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public PlaylistStore getPlaylistStore() {
        return playlistStore;
    }

    public AudioPlayer getPlayer() {
        return player;
    }
}
