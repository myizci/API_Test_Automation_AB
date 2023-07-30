package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlDummyApi {

    protected RequestSpecification specDummyApi;

    @Before
    public void setup(){

        specDummyApi= new RequestSpecBuilder()
                .setBaseUri("http://dummy.restapiexample.com/api/v1")
                .build();
    }
}
