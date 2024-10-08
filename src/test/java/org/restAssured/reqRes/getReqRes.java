package org.restAssured.reqRes;

import io.cucumber.java.sl.In;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.utilities.PropertiesReader;
import org.utilities.YamlReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class getReqRes {
    String propertiesFilePath="src/test/resources/config.properties";
    Response response;
    String URL = YamlReader.readYamlData("src/test/resources/config.yaml","reqresURL");
    SoftAssert softAssert=new SoftAssert();
    @Test
    public void get_single_user() {
        response = given()
                .contentType(ContentType.JSON).pathParam("users", "users")
                .pathParam("id", 2)
                .when()
                .get(URL + "{users}" + "/" + "{id}");
        Assert.assertEquals(response.statusCode(), 200, "Different Status Code Please check the data");
        Assert.assertTrue(response.asPrettyString().contains("Janet"), "Different Value Available, please check");
    }

    @Test
    public void get_list_of_users(){
        response=given().contentType(ContentType.JSON)
                .pathParam("users","users")
                .queryParam("page",1)
                .when().get(URL+"{users}");
        ArrayList<ArrayList<String>> users=new ArrayList<>();
        users.add(response.jsonPath().get("data"));
        Assert.assertTrue(response.asPrettyString().contains("Janet"),"User not found in the list");
    }

    @Test
    public void user_not_available(){
        response=given().contentType(ContentType.JSON)
                .pathParam("users","users")
                .pathParam("id", Integer.parseInt(Objects.
                        requireNonNull(PropertiesReader.readProperties(propertiesFilePath, "id_not_available")))).
                        when().get(URL + "{users}" + "/" + "{id}");
        softAssert.assertEquals(response.getStatusCode(),404,"User Available for given ID");
        softAssert.assertEquals(response.getBody().jsonPath().getString(""),null,"Data is not null");
        softAssert.assertAll();
    }

    @Test
    public void get_list_of_resources(){
        response=given().contentType(ContentType.JSON).
                pathParam("unknown","unknown").
                when().get(URL+"{unknown}");
        List<String> ids=new ArrayList<>();
        if (response == null) Assert.fail();
        if(response.jsonPath().get("data.id")!=null){
        ids.add(response.jsonPath().getList("data.id").toString());
        } else {
            System.out.println("json path might have changed");
        }
        System.out.println(ids);
    }
}
