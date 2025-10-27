package Paribu.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {


    public static Properties properties;

    static {
        String dosyayolu = "configuration.properties";

        try {


            FileInputStream fis = new FileInputStream(dosyayolu);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public static String getProperty(String key){

        return properties.getProperty(key);

    }
}
