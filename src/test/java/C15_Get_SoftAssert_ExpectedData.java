import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C15_Get_SoftAssert_ExpectedData {

    @Test
    public void test01(){

        /*
        http://dummy.restapiexample.com/api/v1/employee/3

        Validate that response body is as follows

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

        // 1- End-point and request body
        String url = " http://dummy.restapiexample.com/api/v1/employee/3";
        // 2- Expected data
        JSONObject expectedData = new JSONObject();
        JSONObject dataInfo = new JSONObject();
        dataInfo.put("id",3);
        dataInfo.put("employee_name","Ashton Cox");
        dataInfo.put("employee_salary",86000);
        dataInfo.put("employee_age",66);
        dataInfo.put("profile_image","");
        dataInfo.put("id",3);

        expectedData.put("status","success");
        expectedData.put("data",dataInfo);
        expectedData.put("message","Successfully! Record has been fetched.");

      //  System.out.println(expectedData.toString());

        // 3- Send request and save the response

        Response response = given().when().get(url);
        // 4- Assert

        JsonPath responseJsonPath = response.jsonPath();

    }
}
