package org.utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class YamlReader {

    public static String readYamlData(String filePath, String requiredField){
        String fieldValue="";
       try {
           File f = new File(filePath);
           FileInputStream fis = new FileInputStream(f);
           Yaml yaml = new Yaml();
           List<Map<String, Object>> yamlList = yaml.load(fis);
           for (Map<String, Object> data : yamlList) {
               if (data.containsKey(requiredField)) {
                   fieldValue = String.valueOf(data.get(requiredField));
                   break; // Exit loop after finding the first match
               }
           }
       } catch (Exception e){
           System.out.println(e.getMessage());
       }
        return fieldValue;
    }

    public static void main(String[] args) {
        System.out.println(readYamlData("src/test/resources/DBconfig.yaml","user_name"));
    }
}
