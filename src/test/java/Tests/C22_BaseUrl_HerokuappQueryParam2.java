package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C22_BaseUrl_HerokuappQueryParam2 extends BaseUrlHerokuapp {

    /*
https://restful-booker.herokuapp.com/booking

Get firstname=Susan lastname=Ericson
Validate status 200, at least one "Susan Ericson"

     */


    @Test
    public void test01(){

        // 1- End-point and request data
        specHerokuapp.pathParam("pp1","booking").queryParams("firstname","Susan","lastname","Jones");
        // 2- Expected data
        // 3- Send request and save response

        Response response= given()
                                    .when().spec(specHerokuapp)
                                    .get("/{pp1}");

       // response.prettyPrint();
        // 4- Assertion

        response.then().assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(2));



    }
}
