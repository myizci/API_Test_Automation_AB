package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C21_BaseUrl_Herokuapp_QueryParam extends BaseUrlHerokuapp {


    /*
https://restful-booker.herokuapp.com/booking

Query -> firstname = Susan  GET

status -> 200 , at least one booking has a firstname Susan

     */

    @Test
    public void test01() {

        // 1- end-point and request body

        specHerokuapp.pathParam("pp1", "booking").queryParam("firstname", "Susan");
        // 2- expected data

        // 3- send request and save response

        Response response = given()
                .when().spec(specHerokuapp)
                .get("/{pp1}");


        response.prettyPrint();

        // 4- Assertion

        response
                .then()
                .assertThat()
                .body("booking",Matchers.hasSize(2));


    }
}
