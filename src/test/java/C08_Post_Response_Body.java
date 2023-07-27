import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C08_Post_Response_Body {
    @Test
    public void test01(){

         /*
        https://jsonplaceholder.typicode.com/posts

        {
    title: "API",
    body: "API is so easy",
    userId: 10,
  }



  Validate that
        status code 201
        content type application/json; charset=utf-8
        userId <100
        title "API"
        body contains "API"

         */

        // 1. Request body and end-point
        String url = "https://jsonplaceholder.typicode.com/posts";
        JSONObject requestBody = new JSONObject();

        requestBody.put("title","API");
        requestBody.put("body","API is so easy");
        requestBody.put("userId",10);
        // 2. Prep expected data

        // 3. Save request response
        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        response.prettyPrint();
        // 4. Assertion

        response
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", Matchers.equalTo("API"))
                .body("userId",Matchers.lessThan(100))
                .body("body", Matchers.containsString("API"));

    }
}
