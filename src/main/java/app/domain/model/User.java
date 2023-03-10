package app.domain.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

public class User {

    private Email email;
    private Password password;
    private String name;
    private Set<UserRole> roles = new HashSet<>();
    private Set<Playlist> playlists = new HashSet<>();

    public User( Email id, Password pwd, String name ) {
        if (ObjectUtils.allNotNull(new Object[]{id, pwd}) && !StringUtils.isBlank(name)) {
            this.email = id;
            this.password = pwd;
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException("User cannot have an id, password or name as null/blank.");
        }
    }

    public Email getId() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public boolean hasId(Email id) {
        return this.email.getEmail().equals(id.getEmail());
    }

    public boolean hasPassword(String pwd) {
        return this.password.checkPassword(pwd);
    }

    public boolean addRole(UserRole role) {
        return role != null && this.roles.add(role);
    }

    public boolean removeRole(UserRole role) {
        return role != null && this.roles.remove(role);
    }

    public boolean hasRole(UserRole role) {
        return this.roles.contains(role);
    }

    public boolean hasRole(String roleId) {
        Iterator var2 = this.roles.iterator();

        UserRole role;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            role = (UserRole)var2.next();
        } while(!role.hasId(roleId));

        return true;
    }

    public List<UserRole> getRoles() {
        List<UserRole> list = new ArrayList();
        Iterator var2 = this.roles.iterator();

        while(var2.hasNext()) {
            UserRole role = (UserRole)var2.next();
            list.add(role);
        }

        return Collections.unmodifiableList(list);
    }

    public boolean changeRoles(List<UserRole> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
        return this.roles.containsAll(roles);
    }

    public boolean addPlaylist(Playlist playlist) {
        return playlist != null && this.playlists.add(playlist);
    }

    public boolean removePlaylist(Playlist playlist) {
        return playlist != null && this.playlists.remove(playlist);
    }

    public boolean changeName(String newName) {
        if (StringUtils.isBlank(newName)) {
            return false;
        } else {
            this.name = newName.trim();
            return true;
        }
    }

    public Playlist getByName(String name) {
        for( Playlist i : this.playlists ) {
            if( i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public void addMusicToPlaylist(Playlist playlist, Music music) {
        playlist.addMusic(music);
    }

    public void insertToDatabase(Connection conn, String name, String email) {
        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO Pers_Playlist (name, email_ID) VALUES (?, ?)");
            stat.setString(1, name);
            stat.setString(2, email);
            stat.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertToDatabase(Connection conn, String name, String email, String file_name) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT pplaylist_ID FROM Pers_Playlist WHERE (email_ID = ? AND name = ?)");
            stmt.setString(1, email);
            stmt.setString(2, name);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int code_ID = rs.getInt("pplaylist_ID");

                PreparedStatement stat = conn.prepareStatement("INSERT INTO PersPlaylist_Music (pplaylist_ID, fileName_ID) VALUES (?, ?)");
                stat.setInt(1, code_ID);
                stat.setString(2, file_name);
                stat.executeUpdate();
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.email.hashCode();
        return hash;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (this.getClass() != o.getClass()) {
            return false;
        } else {
            User obj = (User)o;
            return Objects.equals(this.email, obj.email);
        }
    }

    public String toString() {
        return String.format("%s - %s", this.email.toString(), this.name);
    }

    public List<String> getPlaylistsString() {
        List<String> lst = new ArrayList<>();
        for (Playlist i : this.playlists) {
            String str = i.toString();
            lst.add(str);
        }
        return lst;
    }
}
