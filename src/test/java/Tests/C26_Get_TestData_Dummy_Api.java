package Tests;

import TestData.TestDataDummyApi;
import baseUrl.BaseUrlDummyApi;
import com.sun.source.tree.AssertTree;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C26_Get_TestData_Dummy_Api extends BaseUrlDummyApi {

    /*

    http://dummy.restapiexample.com/api/v1/employee/3

    Get -> status code 200, content type application/json, validate json expected body as follows

            {
        "status": "success",
        "data": {
        "id": 3,
        "employee_name": "Ashton Cox",
        "employee_salary": 86000,
        "employee_age": 66,
        "profile_image": ""
        },
        "message": "Successfully! Record has been fetched."
        }
     */

    @Test
    public void test01(){


        // 1- end-point and request body
        specDummyApi.pathParams("pp1","employee","pp2","3");

        // 2- expected data
        JSONObject expectedData = TestDataDummyApi.jsonDummyApiBody(3,"Ashton Cox",86000,66,"");

        //System.out.println(expectedData.toString());

        // 3- Send request and save response

        Response response = given().spec(specDummyApi)
                                .when()
                                .get("{pp1}/{pp2}");

        //response.prettyPrint();

        // 4- Assertion
        // expected data is a json object
        // response can be turned into a jsonpath

        JsonPath jsonPath = response.jsonPath();

        assertEquals(TestDataDummyApi.statusSuccess,response.statusCode());
        assertEquals(TestDataDummyApi.contentType,response.contentType());
        // assert body
        assertEquals(expectedData.getJSONObject("data").getString("profile_image"),jsonPath.getString("data.profile_image"));
        assertEquals(expectedData.getJSONObject("data").getString("employee_name"),jsonPath.getString("data.employee_name"));
        assertEquals(expectedData.getJSONObject("data").getInt("employee_salary"),jsonPath.getInt("data.employee_salary"));
        assertEquals(expectedData.getJSONObject("data").getInt("id"),jsonPath.getInt("data.id"));
        assertEquals(expectedData.getJSONObject("data").getInt("employee_age"),jsonPath.getInt("data.employee_age"));
        assertEquals(expectedData.getString("message"),jsonPath.getString("message"));
        assertEquals(expectedData.getString("status"),jsonPath.getString("status"));


    }
}
