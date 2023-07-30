package Tests;

import TestData.TestDataJsonPlaceholder;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C24_Get_TestData_Dynamic extends BaseUrlJsonPlaceholder {

     /*

        https://jsonplaceholder.typicode.com/posts/40
        Get -> status code 200, response body as follows

       {
    "userId": 4,
    "id": 40,
    "title": "enim quo cumque",
    "body": "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam"
}

     */

    @Test

    public void test01() {

        // 1- end-point and request body

        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", "40");

        // 2- Expected data
        String title = "enim quo cumque";
        String body = "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam";

        JSONObject expectedData = TestDataJsonPlaceholder.responseJsonBody(4, 40, title, body);

       // System.out.println(expectedData.toString());
        // 3- send query and save response

        Response response = given().spec(specJsonPlaceholder)
                                .when()
                                .get("/{pp1}/{pp2}");

       // response.prettyPrint();
        // 4- Assertion
        assertEquals(TestDataJsonPlaceholder.successStatus, response.statusCode());
        JsonPath responseJsonPath = response.jsonPath();

        assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));

    }


}
