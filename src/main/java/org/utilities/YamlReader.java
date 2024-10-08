package org.utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class YamlReader {

    public static String readYamlData(String requiredFiled){
        String fieldvalue="";
       try {
           File f = new File("src/test/resources/config.yaml");
           FileInputStream fis = new FileInputStream(f);
           Yaml yaml = new Yaml();
           ArrayList<Map<String, Objects>> yamlList= yaml.load(fis);
           for (Map<String, Objects> data : yamlList) {
              fieldvalue=String.valueOf(data.get(requiredFiled));
           }
       } catch (Exception e){
           System.out.println(e.getMessage());
       }
        return fieldvalue;
    }

    public static void main(String[] args) {
        System.out.println(readYamlData("name"));
    }
}
