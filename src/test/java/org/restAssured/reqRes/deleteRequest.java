package org.restAssured.reqRes;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.PropertiesReader;
import org.utilities.YamlReader;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class deleteRequest {
    String URL = YamlReader.readYamlData("src/test/resources/config.yaml", "reqresURL");
    String filePath = "src/test/resources/config.properties";
    Response response;

    @Test
    public void delete_user() {
        response = given().contentType(ContentType.JSON)
                .pathParam("users", "users")
                .pathParam("id", Integer.parseInt(Objects.
                        requireNonNull(PropertiesReader.readProperties(filePath, "id_available")))).
                when().delete(URL + "{users}" + "/" + "{id}");
        Assert.assertEquals(response.getStatusCode(), 204, "Different Status code received than 204");
    }

    @Test
    public void get_delayed_response() {

        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 4000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 4000));

        try {

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .config(config)
                    .pathParam("users", "users")
                    .queryParam("delay", 3)
                    .when()
                    .delete(URL + "{users}");

            // Check response status
            Assert.assertEquals(response.getStatusCode(),204);
        } catch (Exception e) {
            Assert.fail("Request failed with timeout or error: " + e.getMessage());
        }
    }
}


