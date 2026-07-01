package base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static final String BASE_URL = "https://reqres.in/api";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        // log all requests and responses so failures are easy to debug
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
