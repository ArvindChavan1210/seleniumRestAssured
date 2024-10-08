package org.restAssured.herokuAPI;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.apiBaseClass;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.restAssured.herokuAPI.PostMethods.id;

public class DeleteMethod {

    @Test(dependsOnMethods = {"org.restAssured.herokuAPI.PostMethods.createData","org.restAssured.herokuAPI.getMethods.getBookingIDs"})
    public void deleteRequest(){
        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 1000));

        Response response=given().contentType(ContentType.JSON).config(config)
                .cookie("token",TokenApi.GetToken_fromAPI())
                .pathParam("booking","booking")
                .pathParam("id",id).delete(apiBaseClass.getRequiredURL("herokuURL")+"{booking}/{id}");
        System.out.println("Deletion Completed :"+response.statusCode());
        Assert.assertEquals(201,response.getStatusCode());
        for(int i=0; i<getMethods.ids.size(); i++){
            if(!getMethods.ids.get(i).equals(id)){
                System.out.println(id +" not found in the list");
                break;
            }
        }
    }
}
