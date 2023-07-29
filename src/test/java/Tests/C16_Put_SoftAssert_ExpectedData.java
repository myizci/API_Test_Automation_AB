package Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class C16_Put_SoftAssert_ExpectedData {
    @Test
    public  void test01(){

       /*
        http://dummy.restapiexample.com/api/v1/update/21

        Request Body
        {
        "status": "success",
        "data": {
        "name": “Haley Kennedy",
        "salary": "313500",
        "age": "43",
        "id": 14,
        "profile_image": ""
        }
        }

        Response Body
        { "status": "success",
        "data": {
        "status": "success",
        "data": {
        "name": “Ahmet",
        "salary": "313500",
        "age": "43",
        "id": 14,
        "profile_image": ""
         }
        },
        "message": "Successfully! Record has been updated."}
        */

        // 1- end-point and request body

        String url ="https://dummy.restapiexample.com/api/v1/employee/14";

        JSONObject requestBody = new JSONObject();
        JSONObject dataInfo = new JSONObject();

        dataInfo.put("name","Haley Kennedy2");
        dataInfo.put("salary","313500");
        dataInfo.put("age","43");
        dataInfo.put("id",14);
        dataInfo.put("profile_image","");

        requestBody.put("status","success");
        requestBody.put("data",dataInfo);

       // System.out.println(requestBody);
        // 2- Expected Data

        JSONObject expectedData = new JSONObject();

        expectedData.put("status","success");
        expectedData.put("data",requestBody);
        expectedData.put("message","Successfully! Record has been updated.");

      //  System.out.println(expectedData);

       //  3- Send request and save response

        Response response = given().contentType(ContentType.JSON)
                                    .when().body(requestBody.toString())

                                    .put(url);

        response.prettyPrint();

        // 4- Assert
        JsonPath responseJsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();
        // actual , expected
        softAssert.assertEquals(responseJsonPath.get("status"),expectedData.get("status"));
       // softAssert.assertEquals(responseJsonPath.get("message"),expectedData.get("message"));
     //   softAssert.assertEquals(responseJsonPath.get("data.status"),expectedData.getJSONObject("data").get("status"));
       // softAssert.assertEquals(responseJsonPath.get("data.data.name"),expectedData.getJSONObject("data")get("status"));



        softAssert.assertAll();



    }
}
