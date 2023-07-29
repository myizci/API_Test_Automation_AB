package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C07_Get_Response_Body {
    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/44


  Validate that
        status code 200
        content type application/json; charset=utf-8
        userId 5
        title optio dolor molestias sit

         */

        // 1. Request body and end-point
        String url = "https://jsonplaceholder.typicode.com/posts/44";
        // 2. Prep expected data
        // 3. Save request response
        Response response = given().when().get(url);
        response.prettyPrint();
        // 4. Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", Matchers.equalTo(5))
                .body("title",Matchers.equalTo("optio dolor molestias sit"));

    }
}
