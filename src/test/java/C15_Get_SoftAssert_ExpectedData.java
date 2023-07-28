import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

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
        response.prettyPrint();
        // 4- Assert

        JsonPath responseJsonPath = response.jsonPath();

        // We will use SoftAssert, need to create an object for it

        SoftAssert softAssert = new SoftAssert();
      // actual,expected
        softAssert.assertEquals(responseJsonPath.get("status"),expectedData.get("status"));
        softAssert.assertEquals(responseJsonPath.get("message"),expectedData.get("message"));
        softAssert.assertEquals(responseJsonPath.get("data.id"),expectedData.getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_name"),expectedData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_salary"),expectedData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_age"),expectedData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(responseJsonPath.get("data.profile_image"),expectedData.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();

    }
}
