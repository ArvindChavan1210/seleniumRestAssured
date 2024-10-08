package org.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class PropertiesReader {

    public static String readProperties(String filePath, String fieldName) {
        try (FileReader fr = new FileReader(filePath)) {
            Properties properties = new Properties();
            properties.load(fr);
            String value = properties.getProperty(fieldName);
            if (value != null) {
                System.out.println(fieldName+" Selected: " + value);
                return value;
            } else {
                System.out.println("Field not found: " + fieldName);
            }
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String browser = readProperties("src/test/resources/config.properties", "driver");

    }
}
