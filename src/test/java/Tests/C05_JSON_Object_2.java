package Tests;

import org.json.JSONObject;
import org.junit.Test;

public class C05_JSON_Object_2 {

    @Test
    public void test01(){
        /*
        {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
         */

        // Create innermost json object first
        JSONObject dateJsonObject = new JSONObject();
        dateJsonObject.put("checkin", "2018-01-01");
        dateJsonObject.put("checkout", "2019-01-01");

        JSONObject requestBody = new JSONObject();
        requestBody.put("firstname", "Jim");
        requestBody.put("lastname" , "Brown");
        requestBody.put("totalprice" , 111);
        requestBody.put("depositpaid" , true);
        requestBody.put("additionalneeds" , "Breakfast");
        requestBody.put("bookingdates" ,dateJsonObject);

        System.out.println(requestBody);

    }
}
