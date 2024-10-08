package org.utilities;

public class apiBaseClass {

    public static String getRequiredURL(String clientName) {
        return YamlReader.readYamlData("src/test/resources/config.yaml",clientName);
    }
}
