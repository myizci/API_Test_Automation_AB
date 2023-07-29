package Tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C18_BaseUrl_JasonPlaceHolder extends BaseUrlJsonPlaceholder {

    @Test
    public void test01() {
        // 3) https://jsonplaceholder.typicode.com/posts/50
        // Delete -> Status code 200 -> body null

        // 1- end-point and request body

        specJsonPlaceholder.pathParams("pp1","posts","pp2",50);

        // 2- expected data

        // 3- send request and save response

        Response response = given()
                                    .when().spec(specJsonPlaceholder)
                                    .delete("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- assertion

        response.then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.nullValue());


    }

}
