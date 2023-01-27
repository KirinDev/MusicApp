package app.database;

import app.auth.AuthFacade;
import app.controller.App;
import app.domain.model.Music;
import app.domain.model.Playlist;
import app.domain.shared.Constants;
import app.domain.store.MusicStore;
import app.domain.store.PlaylistStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DataLoader {

    private App app;

    public DataLoader() {
        this.app = App.getInstance();
    }

    public void loadData(Connection conn) {
        loadUsersData(conn);
        loadMusicData(conn);
        loadPlaylistData(conn);
    }

    public void loadUsersData(Connection conn) {
        AuthFacade auth = this.app.getKirinDev().getAuthFacade();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM App_User");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String email = rs.getString("email_ID");
                String name = rs.getString("name");
                String pwd = rs.getString("password");

                auth.addUserWithRole(name, email, pwd, Constants.ROLE_USER);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void loadMusicData(Connection conn) {
        MusicStore store = this.app.getKirinDev().getMusicStore();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Music");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String file_name = rs.getString("fileName_ID");
                String name = rs.getString("name");
                String time = rs.getString("time");
                String artist = rs.getString("artist");

                store.add(new Music(name, file_name, time, artist));
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void loadPlaylistData(Connection conn) {
        PlaylistStore store = this.app.getKirinDev().getPlaylistStore();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Global_Playlist");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name_ID");

                PreparedStatement stmt2 = conn.prepareStatement("SELECT fileName_ID FROM GlobalPlaylist_Music WHERE name_ID = ?");
                stmt2.setString(1, name);

                ResultSet rs2 = stmt2.executeQuery();

                Set<Music> musics = new HashSet<>();
                while(rs2.next()) {
                    String file = rs2.getString("fileName_ID");
                    PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM Music WHERE fileName_ID = ?");
                    stmt3.setString(1, file);

                    ResultSet rs3 = stmt3.executeQuery();

                    while(rs3.next()) {
                        String nameMusic = rs3.getString("name");
                        String file_name = rs3.getString("fileName_ID");
                        String time = rs3.getString("time");
                        String artist = rs3.getString("artist");

                        musics.add(new Music(nameMusic, file_name, time, artist));
                    }

                }

                store.add(new Playlist(name, musics));
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}
