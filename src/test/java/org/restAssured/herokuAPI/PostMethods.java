package org.restAssured.herokuAPI;

import commons.ListenerUtils;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.utilities.apiBaseClass;
import org.utilities.jsonReader;
import org.utilities.jsonWriter;

import java.io.FileInputStream;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Listeners(ListenerUtils.class)
public class PostMethods {
    String token=TokenApi.GetToken_fromAPI();
    Response response;
    String URL= apiBaseClass.getRequiredURL("herokuURL");
    static String id;
    String filePath="src/test/java/org/restAssured/herokuAPI/data/createBookingBody.json";

   @Test
   public void createData() {
       try(FileInputStream fis = new FileInputStream(filePath)) {
           response = given().contentType(ContentType.JSON).pathParam("booking","booking").
                   body(fis).
                   when().post(URL+"{booking}");
           id=response.jsonPath().getString("bookingid");
           Map<String, Object> data= (Map<String, Object>) jsonReader.readData("bookingdates",filePath).get("bookingdates");
           SoftAssert softAssert=new SoftAssert();
           softAssert.assertEquals(response.jsonPath().getString("booking.firstname"),jsonReader.readData("firstname",filePath).get("firstname"));
           softAssert.assertEquals(response.jsonPath().getString("booking.lastname"),jsonReader.readData("lastname",filePath).get("lastname"));
           softAssert.assertEquals(response.jsonPath().getString("booking.totalprice"),jsonReader.readData("totalprice",filePath).get("totalprice"));
           softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"),data.get("checkin"));
           softAssert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"),data.get("checkout"));
           softAssert.assertAll();
       } catch (Exception e){
           System.out.println(e.getMessage());
       }
    }

   @Test(dependsOnMethods = "createData")
   public void UpdateBooking() {
       jsonWriter.updateJson("firstname","Lion",filePath);
        jsonWriter.updateJson("lastname","King",filePath);
        try(FileInputStream fis=new FileInputStream("src/test/java/org/restAssured/herokuAPI/data/createBookingBody.json")) {
            response = given()
                    .contentType(ContentType.JSON)
                    .cookie("token", token)
                    .pathParam("booking", "booking")
                    .pathParam("id", id)
                    .body(fis)
                    .when()
                    .put(URL + "{booking}/{id}");

            // Print and validate response
            response.getBody().prettyPrint();
            assertEquals(response.getStatusCode(), 200, "Expected status code 200");
            SoftAssert softAssert=new SoftAssert();
            softAssert.assertEquals(response.jsonPath().getString("firstname"),jsonReader.readData("firstname",filePath).get("firstname"));
            softAssert.assertEquals(response.jsonPath().getString("lastname"),jsonReader.readData("lastname",filePath).get("lastname"));
            softAssert.assertAll();
        } catch (Exception e) {
            // Fail the test if an exception occurs
            Assert.fail("Exception occurred while updating booking: " + e.getMessage());
        }
    }
   @Test(dependsOnMethods = "createData")
   public void PartialUpdateBooking(){
       JSONObject object=new JSONObject();
       object.put("firstname","jim");
       object.put("lastname","jam");
       RestAssuredConfig config = RestAssured.config()
               .httpClient(HttpClientConfig.httpClientConfig()
                       .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000)
                       .setParam(CoreConnectionPNames.SO_TIMEOUT, 1000));
       response=given().contentType(ContentType.JSON).cookie("token",TokenApi.GetToken_fromAPI())
               .config(config)
               .pathParam("booking","booking")
               .pathParam("id",id)
               .body(object.toString())
               .when()
               .patch(URL+"{booking}/{id}");

       SoftAssert softAssert=new SoftAssert();
       softAssert.assertEquals(response.statusCode(),200, "Different Status Code, Please check execution");
       softAssert.assertEquals(object.get("firstname"),response.jsonPath().getString("firstname"));
       softAssert.assertEquals(object.get("lastname"),response.jsonPath().getString("lastname"));
       softAssert.assertAll();
   }

}
