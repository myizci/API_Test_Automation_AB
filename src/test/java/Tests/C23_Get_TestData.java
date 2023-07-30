package Tests;

import TestData.TestDataJsonPlaceholder;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C23_Get_TestData extends BaseUrlJsonPlaceholder {

    /*

        https://jsonplaceholder.typicode.com/posts/22
        Get -> status code 200, response body as follows

        {
        "userId": 3,
        "id": 22,
        "title": "dolor sint quo a velit explicabo quia nam",
        "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
        um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }

     */


    @Test
    public void test01() {

        // 1- End-point and request body
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", "22");

        // 2- Expected data
        JSONObject expectedData = TestDataJsonPlaceholder.responseBody22();
        //System.out.println(expectedData);

        // 3- Send request and save response

        Response response = given().spec(specJsonPlaceholder)
                                  .when()
                                .get("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assertion

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(TestDataJsonPlaceholder.successStatus, response.statusCode());
        Assert.assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));


    }
}
