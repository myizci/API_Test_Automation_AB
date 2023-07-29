package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class C09_Get_Body_RemoveRepetition {

    @Test
    public void test01() {

        /*
        https://restful-booker.herokuapp.com/booking/10

        Validate that GET request
        status code 200
        content type application/json
        "firstname" "Susan"
        "lastname" "Jackson"
        "totalprice" less than 1000
        "depositpaid" true
        "additionalneeds" not empty

         */

        // 1. Request body and end-point
        String url = "https://restful-booker.herokuapp.com/booking/10";
        // 2. Prep expected data
        // 3. Save request response
        Response response = given().when().get(url);

        // 4. Assertion
        response.prettyPrint();
/*
Method 1
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Susan"))
                .body("lastname", Matchers.equalTo("Jackson"))
                .body("totalprice",Matchers.lessThan(1000))
                .body("depositpaid",Matchers.equalTo(true))
                .body("additionalneeds",Matchers.notNullValue());

 */

        // Method 2

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Susan"),
        "lastname", equalTo("Jones"),
        "totalprice", lessThan(1000),
        "depositpaid", equalTo(true),
        "additionalneeds",nullValue());

















    }
}
