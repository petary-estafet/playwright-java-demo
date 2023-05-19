package utils;

import java.util.ResourceBundle;

public class ResourcesBundles {

    public static String getEnvProperty(String property) {
        ResourceBundle environmentBundle = ResourceBundle.getBundle("env");
        return environmentBundle.getString(property);
    }
}
