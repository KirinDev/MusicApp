package app.mappers;

import app.domain.model.Music;
import app.mappers.dto.MusicDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MusicMapper {

    public MusicMapper() { }

    public MusicDTO toDTO(Music music) {
        return new MusicDTO(music.getName(), music.getFile_name(), music.getTime(), music.getArtist());
    }

    public List<MusicDTO> toDTO(Set<Music> musics) {
        List<MusicDTO> musicsDTO = new ArrayList<>();

        for (Music music : musics) {
            musicsDTO.add(this.toDTO(music));
        }

        return musicsDTO;
    }
}
