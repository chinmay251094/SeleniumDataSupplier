package com.digicorp.utils;

import com.digicorp.constants.FrameworkConstants;
import com.digicorp.enums.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class ConfigReaderUtils {
    private ConfigReaderUtils() {}

    private static final Map<String, String> CONFIGMAP = new HashMap<>();
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(FrameworkConstants.getPropertyFilePath());
            properties.load(fis);

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String get(Config key) throws Exception {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new Exception("Property name " + key + " is not found. Check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

    /*public static String getValue(String key) throws Exception {
        if (Objects.isNull(properties.getProperty(key)) || Objects.isNull(key)) {
            throw new Exception("Property name " + key + " is not found. Check config.properties");
        } else {
            return properties.getProperty(key);
        }
    }*/
}
