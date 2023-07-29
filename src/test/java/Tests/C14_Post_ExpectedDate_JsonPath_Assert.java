package Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class C14_Post_ExpectedDate_JsonPath_Assert {

    @Test
    public void test01(){
        /*

            https://restful-booker.herokuapp.com/booking

            Request body
            {
            "firstname" : "Ahmet",
            "lastname" : “Gunes",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
            "checkin" : "2021-06-01",
            "checkout" : "2021-06-10"
            },
            "additionalneeds" : "wi-fi"
            }

            Response Body
            {
            "bookingid": 24,
            "booking": {
            "firstname": "Ahmet",
            "lastname": "Gunes",
            "totalprice": 500,
            "depositpaid": false,
            "bookingdates": {
            "checkin": "2021-06-01",
            "checkout": "2021-06-10"
            },
            "additionalneeds": "wi-fi"
            }
            }

         */

        // 1- End point and request body

        String url = " https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin","2021-06-01");
        bookingDates.put("checkout","2021-06-10");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingDates);
        requestBody.put("additionalneeds","wi-fi");

      //  System.out.println(requestBody.toString());

        // 2- Expected Data

        JSONObject expectedData = new JSONObject();

        expectedData.put("bookingid",24);
        expectedData.put("booking", requestBody);

        //System.out.println(expectedData.toString());

        // 3- Send request and save the response

        Response response = given().contentType(ContentType.JSON)
                .when()
                .body(requestBody.toString())
                .post(url);

        // 4- Assertion

        //response.prettyPrint();

        JsonPath responseJsonPath = response.jsonPath();
        // ("expected","actual") -> (jsonObject, response.jsonpath)
        assertEquals(expectedData.getJSONObject("booking").get("firstname"),responseJsonPath.get("booking.firstname"));
        assertEquals(expectedData.getJSONObject("booking").get("lastname"),responseJsonPath.get("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").get("totalprice"),responseJsonPath.get("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),responseJsonPath.get("booking.additionalneeds"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),responseJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),responseJsonPath.get("booking.bookingdates.checkout"));

    }
}
