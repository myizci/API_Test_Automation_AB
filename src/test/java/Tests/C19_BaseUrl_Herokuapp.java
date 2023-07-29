package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class C19_BaseUrl_Herokuapp extends BaseUrlHerokuapp {

    /*

    https://restful-booker.herokuapp.com/booking

    Get request
    status code 200
    12 bookings in total

     */

    @Test
    public void test01() {
        // 1- End-point and request body

        specHerokuapp.pathParam("pp1", "booking");
        // 2- Expected data
        // 3- Send request and save response

        Response response = given()
                .when().spec(specHerokuapp)
                .get("/{pp1}");

       //response.prettyPrint();
        // 4- Assertion

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getList("bookingid").size());
        response
                .then()
                .assertThat()
                .statusCode(200)
              //  .body("size()", is(1498))
               ;

    }


}
