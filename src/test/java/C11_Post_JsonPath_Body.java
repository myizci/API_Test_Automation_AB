import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class C11_Post_JsonPath_Body {

    @Test
    public void test01() {

        /*
        https://restful-booker.herokuapp.com/booking

        {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
            "checkin" : "2023-01-10",
            "checkout" : "2023-01-20"
                                        },
            "additionalneeds" : "wi-fi"
           }

           Validate for response
        status code 200
        content type application/json
        "firstname" "Ahmet"
        "lastname" "Bulut"
        "totalprice" 500
        "depositpaid" false
        "checkin"  "2023-01-10",
         "checkout"  "2023-01-20"
        "additionalneeds" wi-fi
         */


        // 1- end-point & request body

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject reservationDates = new JSONObject();

        reservationDates.put("checkin","2023-01-10");
        reservationDates.put("checkout","2023-01-20");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice","500");
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",reservationDates);
        requestBody.put("additionalneeds","wi-fi");

        // 2- expected data
        // 3- send request, save response
        Response response= given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

       //4 - Assertion
//        Validate for response
//        status code 200
//        content type application/json
//        "firstname" "Ahmet"
//        "lastname" "Bulut"
//        "totalprice" 500
//        "depositpaid" false
//        "checkin"  "2023-01-10",
//                "checkout"  "2023-01-20"
//        "additionalneeds" wi-fi

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Ahmet"),
        "booking.lastname",equalTo("Bulut"),
                        "booking.totalprice",equalTo(500),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2023-01-10"),
                        "booking.bookingdates.checkout",equalTo("2023-01-20"));


    }
}
