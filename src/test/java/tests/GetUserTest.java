package tests;

import base.BaseTest;
import base.RequestSpecFactory;
import endpoints.UserEndpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUserTest extends BaseTest {

    @Test(description = "GET existing user returns 200 with correct user data")
    public void testGetSingleUser() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 2)
        .when()
            .get(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", notNullValue())
            .body("data.first_name", notNullValue())
            .body("data.last_name", notNullValue());
    }

    @Test(description = "GET non-existent user should return 404")
    public void testGetUserNotFound() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 99999)
        .when()
            .get(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(404)
            .body(equalTo("{}"));
    }

    @Test(description = "GET user list returns paginated results with correct structure")
    public void testGetUserList() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .queryParam("page", 1)
        .when()
            .get(UserEndpoints.USERS)
        .then()
            .statusCode(200)
            .body("page", equalTo(1))
            .body("data", hasSize(greaterThan(0)))
            .body("total", greaterThan(0))
            .body("per_page", greaterThan(0));
    }

    @Test(description = "GET single user response matches expected JSON schema")
    public void testGetUserSchema() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 2)
        .when()
            .get(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-schema.json"));
    }

    @Test(description = "Page 2 should contain different users than page 1")
    public void testPaginationReturnsDifferentData() {

        String page1FirstEmail = given()
            .spec(RequestSpecFactory.getRequestSpec())
            .queryParam("page", 1)
        .when()
            .get(UserEndpoints.USERS)
        .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getString("data[0].email");

        String page2FirstEmail = given()
            .spec(RequestSpecFactory.getRequestSpec())
            .queryParam("page", 2)
        .when()
            .get(UserEndpoints.USERS)
        .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getString("data[0].email");

        Assert.assertNotEquals(
                page1FirstEmail,
                page2FirstEmail,
                "Users on page 1 and page 2 should be different");
    }
}