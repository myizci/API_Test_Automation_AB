package Tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
public class C20_BaseUrl_Herokuapp2 extends BaseUrlHerokuapp {

    /*

        https://restful-booker.herokuapp.com/booking

        Post
        {
        "firstname" : "Ahmet",
        "lastname" : “Bulut",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
        "checkin" : "2021-06-01",
        "checkout" : "2021-06-10"
        },
        "additionalneeds" : "wi-fi"
        }

        status -> 200  "firstname" -> "Ahmet"

     */
    @Test
    public void test(){

        // 1- end-point and request body

        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin","2021-06-01");
        bookingDates.put("checkout","2021-06-10");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Arda");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("additionalneeds","wi-fi");
        requestBody.put("bookingdates",bookingDates);

        // 2- Expected data

        // 3- Send request and save response
        Response response= given().contentType(ContentType.JSON)
                .when().spec(specHerokuapp)
                .body(requestBody.toString())
                .log().all()
                .post("/{pp1}");

        // 4- Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname",Matchers.equalTo("Ahmet"));


    }
}
