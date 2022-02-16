package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    public static Properties readPropertiesFile() {
        InputStream inputStream;

        Properties properties = new Properties();
        String propFileName = "qa.properties";
        try {
            inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return properties;
    }
}