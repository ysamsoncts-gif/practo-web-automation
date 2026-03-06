package utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();
    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) props.load(is);
        } catch (Exception ignored) {
        }
    }
    public static String get(String key) {
        return props.getProperty(key);
    }
    public static String get(String key, String defaultValue) {
        String val = props.getProperty(key);
        return (val == null || val.trim().isEmpty()) ? defaultValue : val.trim();
    }
    public static boolean getBoolean(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}