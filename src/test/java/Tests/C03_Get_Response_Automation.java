package Tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C03_Get_Response_Automation {

    @Test
    public void test02(){
        /*
        https://restful-booker.herokuapp.com/booking/10
        Validate that
        status code 200
        content type application/json; charset=utf-8
        server name Cowboy
        status line HTTP/1.1 200 OK

         */

        // 1. Request body and end-point
        String url = "https://restful-booker.herokuapp.com/booking/10";
        // 2. Prep expected data
        // 3. Save request response
        Response response = given().when().get(url);
        // 4. Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");

    }
}
