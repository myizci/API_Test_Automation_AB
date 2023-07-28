import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class C12_Get_ResponseBodyList {

    @Test
    public void test01(){

        /*
        http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
        validate that
        status code 200,
        content type Aplication.JSON,
         body has
        employees number 24
        obe employee is  "Ashton Cox"
        61,21 and 35 among the age list
         */

        // 1- Endpoint and request body

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // 2- Expected data
        // 3- Send request and save response
        Response response = given()
                            .when()
                            .get(url);
        // 4- Assertions
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", hasSize(24),
                        "data.employee_name",hasItem("Ashton Cox"),
                        "data.employee_age", hasItems(61,21,35));

    }
}
