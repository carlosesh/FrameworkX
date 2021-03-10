package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    Path resourceDirectory = Paths.get("src","main","resources", "configs");
    String absolutePath = resourceDirectory.toFile().getAbsolutePath();
    private final String propertyFilePath= absolutePath + "/Configuration.properties";


    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getEmployeesEndPoint() {
        String url = properties.getProperty("employeesEndpoint");
        if(url != null) return url;
        else throw new RuntimeException("employeesEndpoint not specified in the Configuration.properties file.");
    }

}
