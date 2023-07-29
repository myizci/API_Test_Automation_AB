package Tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C17_BaseUrl_JsonPlaceholder extends BaseUrlJsonPlaceholder {

    /*

    Create 3 different method for each situation

    1)  https://jsonplaceholder.typicode.com/posts

    Get request -> Status code 200 -> Response has 100 records

    2) https://jsonplaceholder.typicode.com/posts/44

    Get request -> Status code 200 -> title "optio dolor molestias sit"

    3) https://jsonplaceholder.typicode.com/posts/50

    Delete -> Status code 200 -> body null

     */

    @Test
    public void test01() {

        //1)  https://jsonplaceholder.typicode.com/posts
        //  Get request -> Status code 200 -> Response has 100 records

        // 1- end point and request body

        specJsonPlaceholder.pathParam("pp1","posts");

        // 2- Expected data

        // 3- send request and save response

        Response response = given()
                                    .when().spec(specJsonPlaceholder)
                                    .get("/{pp1}");
      //  response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.hasSize(100));

    }

    @Test
    public void test02() {
       // 2) https://jsonplaceholder.typicode.com/posts/44
        //Get request -> Status code 200 -> title "optio dolor molestias sit"


       // 1- end point and request body
        specJsonPlaceholder.pathParams("pp1","posts","pp2",44);

        // 2- Expected data

        // 3- Send request and save response

        Response response = given()
                .when().spec(specJsonPlaceholder)
                .get("/{pp1}/{pp2}");

       // response.prettyPrint();

        // 4- Assertion

        response.then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("optio dolor molestias sit"));


    }

    @Test
    public void test03() {
        // 3) https://jsonplaceholder.typicode.com/posts/50
        // Delete -> Status code 200 -> body null

        // 1- end-point and request body

        // 2- expected data

        // 3- send request and save response

        // 4- assert


    }


}
