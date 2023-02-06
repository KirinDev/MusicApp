package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.regex.Pattern;

public class Music {

    private String name;
    private String file_name;
    private String time;
    private String artist;

    public Music(String name, String file_name, String time, String artist) {
        if (!StringUtils.isBlank(name) && !StringUtils.isBlank(file_name) && !StringUtils.isBlank(time) && !StringUtils.isBlank(artist)) {
            this.name = name;
            this.file_name = file_name;
            this.time = time;
            this.artist = artist;
        }else{
            throw new IllegalArgumentException("Music cannot have a name, file_name, time and artist null/blank.");
        }

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

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", file_name='" + file_name + '\'' +
                ", time='" + time + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
