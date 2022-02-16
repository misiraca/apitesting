package client.requests;

import utils.ConfigProperties;

import java.util.Properties;

public class Config {

    private static final Properties PROPERTIES = ConfigProperties.readPropertiesFile();
    public static final String REQRES_IN_ENDPOINT = PROPERTIES.getProperty("ENV");
    public static final String USERS_PATH = PROPERTIES.getProperty("USERS_PATH");

    //https://restcountries.com/v3.1/
    public static final String REST_COUNTRIES_IN_ENDPOINT = PROPERTIES.getProperty("ENV_REST_COUNTRIES");


}
