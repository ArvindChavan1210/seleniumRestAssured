package org.utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWriter {

    public static void writePropertiesFile(String filePath, String fieldName,String update){
        try(FileReader fileReader=new FileReader("src/test/resources/config.properties")){
            Properties P=new Properties();
            P.load(fileReader);
            P.setProperty(fieldName,update);

            FileWriter fr=new FileWriter(filePath);
            P.store(fr,null);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        writePropertiesFile("src/test/resources/config.properties","driver","chrome");
    }
}
