package tests;

import base.BaseTest;
import base.RequestSpecFactory;
import endpoints.UserEndpoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeApiTest extends BaseTest {

    // --- Invalid ID ---

    @Test(description = "GET user with non-existent numeric ID should return 404")
    public void testGetUserWithInvalidId() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 9999)
        .when()
            .get(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(404)
            .body(equalTo("{}"));
    }

    @Test(description = "DELETE on non-existent user id should still return 204")
    public void testDeleteNonExistentUser() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 9999)
        .when()
            .delete(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(204);
    }

    // --- Missing required fields ---

    @Test(description = "POST register with missing password should return 400 with error message")
    public void testRegisterMissingPassword() {
        String body = "{\"email\": \"test@reqres.in\"}";

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(body)
        .when()
            .post(UserEndpoints.REGISTER)
        .then()
            .statusCode(400)
            .body("error", equalTo("Missing password"));
    }

    @Test(description = "POST register with missing email should return 400")
    public void testRegisterMissingEmail() {
        String body = "{\"password\": \"somepassword\"}";

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(body)
        .when()
            .post(UserEndpoints.REGISTER)
        .then()
            .statusCode(400)
            .body("error", notNullValue());
    }

    @Test(description = "POST login with missing password should return 400")
    public void testLoginMissingPassword() {
        String body = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(body)
        .when()
            .post(UserEndpoints.LOGIN)
        .then()
            .statusCode(400)
            .body("error", equalTo("Missing password"));
    }

    @Test(description = "POST login with unregistered email should return 400")
    public void testLoginUnregisteredUser() {
        String body = "{\"email\": \"notauser@fake.com\", \"password\": \"abc123\"}";

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(body)
        .when()
            .post(UserEndpoints.LOGIN)
        .then()
            .statusCode(400)
            .body("error", notNullValue());
    }

    // --- Empty / malformed body ---

    @Test(description = "POST create user with empty body should still return 201 — verifies API tolerance")
    public void testCreateUserWithEmptyBody() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body("{}")
        .when()
            .post(UserEndpoints.USERS)
        .then()
            .statusCode(201)
            .body("id", notNullValue());
    }
}