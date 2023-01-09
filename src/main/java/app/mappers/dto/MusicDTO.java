package app.mappers.dto;

public class MusicDTO {

    private String name;
    private String file_name;
    private String time;
    private String artist;

    public MusicDTO(String name, String file_name, String time, String artist) {
        this.name = name;
        this.file_name = file_name;
        this.time = time;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getTime() {
        return time;
    }

    public String getArtist() {
        return artist;
    }
}
