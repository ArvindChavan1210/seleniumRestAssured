package org.restAssured.reqRes;

import commons.ListenerUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.utilities.PropertiesReader;
import org.utilities.YamlReader;
import org.utilities.jsonWriter;

import java.io.FileInputStream;

import static io.restassured.RestAssured.given;

//@Listeners(ListenerUtils.class)
public class postReqRes {

    String URL= YamlReader.readYamlData("src/test/resources/config.yaml","reqresURL");
    Response response;
    String propertiesFilePath="src/test/resources/config.properties";
    String post_body_FilePath="src/test/java/org/restAssured/reqRes/postBody.json";
    String put_body_FilePath="src/test/java/org/restAssured/reqRes/putBody.json";


    @Test
    public void post_reqRes(){
        try {
            response = given().contentType(ContentType.JSON)
                    .pathParam("users", "users")
                    .pathParam("id", PropertiesReader.readProperties(propertiesFilePath, "id_available"))
                    .body(new FileInputStream(post_body_FilePath))
                    .when().post(URL+"{users}"+"/"+"{id}");
            response.body().prettyPrint();
            Assert.assertEquals(response.getStatusCode(),201,"Data not created on target resource");
            Assert.assertTrue(response.getBody().asPrettyString().contains("morpheus"),"Different name created");
            Assert.assertTrue(response.getBody().asPrettyString().contains("leader"),"Different job created");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void get_put_request(){
        try {
            response = given().contentType(ContentType.JSON)
                    .pathParam("users", "users")
                    .pathParam("id", PropertiesReader.readProperties(propertiesFilePath, "id_available"))
                    .body(new FileInputStream(put_body_FilePath))
                    .when().put(URL + "{users}" + "/" + "{id}");
            Assert.assertTrue(response.getBody().asPrettyString().contains("Zakar"),"Different Name Placed in put");
            Assert.assertTrue(response.getBody().asPrettyString().contains("engineer"),"Different Job Placed in put");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void get_patch_request(){
       jsonWriter.updateJson("job","engineer",put_body_FilePath);
        try {
            response = given().contentType(ContentType.JSON)
                    .pathParam("users", "users")
                    .pathParam("id", PropertiesReader.readProperties(propertiesFilePath, "id_available"))
                    .body(new FileInputStream(put_body_FilePath))
                    .when().patch(URL + "{users}" + "/" + "{id}");
            response.body().prettyPrint();
            Assert.assertTrue(response.getBody().asPrettyString().contains("Zakar"),"Different Name Placed in put");
            Assert.assertTrue(response.getBody().asPrettyString().contains("engineer"),"Different Job Placed in put");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
