package Tests;

import TestData.TestDataJsonPlaceholder;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C25_Put_TestData extends BaseUrlJsonPlaceholder {

/*
        https://jsonplaceholder.typicode.com/posts/70

        PUT -> status 200, content type application/json; charset=utf-8, Connection header keep-alive

        Request Body
        {
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
        }

        Expected Data :
        {
        "title": "Ahmet",
        "body": "Merhaba",
        "userId": 10,
        "id": 70
        }

 */
    @Test
    public void test01(){


        // 1- end-point and request data

        specJsonPlaceholder.pathParams("pp1","posts","pp2","70");

        JSONObject requestBody = TestDataJsonPlaceholder.responseJsonBody(10,70,"Ahmet","Merhaba");


        // 2- Expected data

        JSONObject expectedData = TestDataJsonPlaceholder.responseJsonBody(10,70,"Ahmet","Merhaba");

        // 3- Send request and save response

        Response response = given().spec(specJsonPlaceholder)
                            .when().body(requestBody.toString()).contentType(ContentType.JSON)
                             .put("{pp1}/{pp2}")
                ;

        //response.prettyPrint();
        // 4- Assertion

        JsonPath responseJsonPath = response.jsonPath();

        // status 200, content type application/json; charset=utf-8, Connection header keep-alive
        assertEquals(TestDataJsonPlaceholder.successStatus,response.statusCode());
        assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());
        assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));
        assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));


    }
}
