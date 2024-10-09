package org.restAssured.herokuAPI;

import commons.ListenerUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.utilities.YamlReader;
import java.util.List;

import static io.restassured.RestAssured.*;

@Listeners(ListenerUtils.class)
public class getMethods {
    String yamlFilePath="src/test/resources/config.yaml";
    Response response;
    String URL=YamlReader.readYamlData(yamlFilePath,"herokuURL");
    static List<Object> ids;

    @Test
    public void getBookingIDs(){
        response=given().contentType(ContentType.JSON).pathParam("booking","booking")
                .when().get(URL+"{booking}");
        ids = response.body().jsonPath().getList("bookingid");
        Assert.assertFalse(ids.isEmpty(),"List is Empty");
    }

    @Test
    public void getBooking(){
        response=given().contentType(ContentType.JSON).pathParam("booking","booking").
                pathParam("id","1")
                .when().get(URL+"{booking}/{id}");
        response.body().prettyPrint();
//        SoftAssert softAssert=new SoftAssert();
//        softAssert.assertEquals(response.jsonPath().getString("firstname"),"Susan");
//        softAssert.assertEquals(response.jsonPath().getString("lastname"),"Jackson");
//        softAssert.assertEquals(response.jsonPath().getString("totalprice"),"302");
//        softAssert.assertEquals(response.jsonPath().getString("depositpaid"),"true");
//        softAssert.assertAll();
    }

    @Test
    public void gethelathCheck(){
        response= given().contentType(ContentType.JSON).pathParam("ping","ping").
                when().get(URL+"{ping}");
        Assert.assertEquals(201,response.getStatusCode(),"Please Check URL or Connection");

    }
}
