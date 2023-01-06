package app.properties;

import java.io.*;
import java.util.Properties;

public class PropertiesCache {

    private final Properties configProp = new Properties();

    public PropertiesCache() throws FileNotFoundException {
        InputStream in = getClass().getResourceAsStream("/config/config.properties");
        System.out.println("Reading all properties from the file");
        try {
            configProp.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return configProp.getProperty(key);
    }

    public void setProperty(String key, String value){
        configProp.setProperty(key, value);
    }

    public void saveProperty() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("config/config.properties");
        try {
            configProp.store(out, "");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
