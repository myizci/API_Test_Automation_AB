import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C01_GetRequest_ResponseBody {
    @Test
    public void get01(){

        // https://restful-booker.herokuapp.com/booking/10
        // print to console

        // 1. Request body and end-point
        // 2. Prep expected data (payload)
        // 3. Save request response
        // 4. Assertion


        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = given().when().get(url);
        response.prettyPrint();


    }

}
