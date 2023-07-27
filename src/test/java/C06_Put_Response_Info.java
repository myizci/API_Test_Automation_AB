import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_Put_Response_Info {
    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/70
        {
    title: "Ahmet",
    body: "Merhaba",
    userId: 10,
    "id":70
  }

  Validate that
        status code 200
        content type application/json; charset=utf-8
        server name cloudflare
        status line HTTP/1.1 200 OK

         */

        // 1. Request body and end-point
        String url = " https://jsonplaceholder.typicode.com/posts/70";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Jim");
        requestBody.put("body" , "Brown");
        requestBody.put("userId" , 10);
        requestBody.put("id" , 70);
        // 2. Prep expected data
        // 3. Save request response
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody.toString())
                .put(url);

        // 4. Assertion

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");
    }
}
