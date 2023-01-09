package app.mappers;

import app.domain.model.Playlist;
import app.mappers.dto.MusicDTO;
import app.mappers.dto.PlaylistDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlaylistMapper {

    private MusicMapper mapper = new MusicMapper();

    public PlaylistMapper() { }

    public PlaylistDTO toDTO(Playlist playlist) {
        List<MusicDTO> musicsDTO = this.mapper.toDTO(playlist.getMusics());
        return new PlaylistDTO(playlist.getName(), musicsDTO);
    }

    public List<PlaylistDTO> toDTO(Set<Playlist> playlists) {
        List<PlaylistDTO> playlistsDTO = new ArrayList<>();

        for (Playlist playlist : playlists) {
            playlistsDTO.add(this.toDTO(playlist));
        }

        return playlistsDTO;
    }
}
