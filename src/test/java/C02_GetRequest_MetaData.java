import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class C02_GetRequest_MetaData {
    @Test
    public void test01(){
        /*
        status code 200
        content type
        server name
        status line
        response time
         */

        // 1. Request body and end-point
        String url = "https://restful-booker.herokuapp.com/booking/10";
        // 2. Prep expected data (payload)
        // 3. Save request response
        Response response= given().when().get(url);

        System.out.println("status code: "+response.getStatusCode()+
                "\ncontent type: " + response.getContentType()+
                "\nserver head: " + response.getHeader("Server")+
                "\nstatus line: " + response.getStatusLine()+
                "\nresponse time: " + response.getTime()+"ms");

        // 4. Assertion

    }
}
