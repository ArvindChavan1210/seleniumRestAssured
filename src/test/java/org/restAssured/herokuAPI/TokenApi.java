package org.restAssured.herokuAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.utilities.YamlReader;
import java.io.File;
import java.io.FileInputStream;

import static io.restassured.RestAssured.given;

public class TokenApi {

    static String TokenKey;
    static String yamlFilePath="src/test/resources/config.yaml";

    public static String GetToken_fromAPI() {
       try {
           String URL = YamlReader.readYamlData(yamlFilePath,"herokuURL");
           File f = new File("src/test/java/org/restAssured/herokuAPI/data/heroKuTokenBody.json");
           FileInputStream fis = new FileInputStream(f);
           Response response=given().contentType(ContentType.JSON).pathParam("auth","auth").
                body(fis).post(URL+"{auth}");
           TokenKey=response.jsonPath().getString("token");
       } catch (Exception e){
           System.out.println(e.getMessage());
       }
        System.out.println("Token Obtained is : "+TokenKey);
       return TokenKey;

    }


}
