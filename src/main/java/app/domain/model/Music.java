package app.domain.model;

import java.io.File;
import java.util.regex.Pattern;

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

    public boolean checkTimeFormat(String time) {
        String timeRegex = "[0-9][0-9]:[0-9][0-9]";
        Pattern pat = Pattern.compile(timeRegex);
        return pat.matcher(time).matches();
    }

    public boolean fileExists(String file_name) {
        String path = String.format("music/%s", file_name);
        try{
            new File(path);
            return true;
        }catch(NullPointerException e){
            System.err.println("« Error: File '" + file_name + "' not found »");
            return false;
        }

    }
}
