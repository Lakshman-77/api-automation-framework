package base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        // Log all requests and responses for easier debugging
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}