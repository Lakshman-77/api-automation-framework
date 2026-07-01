package tests;

import base.BaseTest;
import base.RequestSpecFactory;
import endpoints.UserEndpoints;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseTest {

    @Test(description = "PUT should replace all user fields and return 200")
    public void testFullUpdate() {
        User updatedData = new User("Ravi Kumar", "Senior QA Engineer");

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 2)
            .body(updatedData)
        .when()
            .put(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("name", equalTo("Ravi Kumar"))
            .body("job", equalTo("Senior QA Engineer"))
            .body("updatedAt", notNullValue());
    }

    @Test(description = "PATCH should partially update and only change specified fields")
    public void testPartialUpdate() {
        String body = "{\"job\": \"Tech Lead\"}";

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 2)
            .body(body)
        .when()
            .patch(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("job", equalTo("Tech Lead"))
            .body("updatedAt", notNullValue());
    }

    @Test(description = "updatedAt should be a valid ISO-8601 timestamp")
    public void testUpdatedAtTimestamp() {
        User updatedData = new User("Time Check", "Engineer");

        String updatedAt = given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 2)
            .body(updatedData)
        .when()
            .put(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getString("updatedAt");

        Assert.assertNotNull(updatedAt, "updatedAt should not be null");

        try {
            Instant.parse(updatedAt);
        } catch (Exception e) {
            Assert.fail("Invalid ISO-8601 timestamp: " + updatedAt);
        }
    }

    @Test(description = "PUT with only name field should still return 200")
    public void testPutWithNameOnly() {
        String body = "{\"name\": \"Priya Singh\"}";

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 3)
            .body(body)
        .when()
            .put(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("name", equalTo("Priya Singh"));
    }
}