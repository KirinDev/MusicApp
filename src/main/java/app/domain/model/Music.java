package app.domain.model;

public class Music {

    private String name;
    private String file_name;
    private String time;
    private String artist;

    public Music(String name, String file_name, String time, String artist) {
        this.name = name;
        this.file_name = file_name;
        this.time = time;
        this.artist = artist;
    }

    public boolean hasNameArtist(String name, String artist) {
        return this.name.equals(name) && this.artist.equals(artist);
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
