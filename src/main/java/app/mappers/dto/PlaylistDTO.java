package app.mappers.dto;

import java.util.List;

public class PlaylistDTO {

    private String name;
    private List<MusicDTO> musics;

    public PlaylistDTO(String name, List<MusicDTO> musics) {
        this.name = name;
        this.musics = musics;
    }

    public String getName() {
        return name;
    }

    public List<MusicDTO> getMusics() {
        return musics;
    }
}
