package tests;

import base.BaseTest;
import base.RequestSpecFactory;
import endpoints.UserEndpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {

    @Test(description = "POST new user returns 201 with name, job, and generated id")
    public void testCreateUser() {
        User payload = new User("Arjun Sharma", "Software Engineer Intern");

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(payload)
        .when()
            .post(UserEndpoints.USERS)
        .then()
            .statusCode(201)
            .body("name", equalTo("Arjun Sharma"))
            .body("job", equalTo("Software Engineer Intern"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue());
    }

    @Test(description = "Created user should have a non-empty string id")
    public void testCreatedUserHasId() {
        User payload = new User("Test User", "QA Intern");

        String id = given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(payload)
        .when()
            .post(UserEndpoints.USERS)
        .then()
            .statusCode(201)
            .extract().jsonPath().getString("id");

        Assert.assertNotNull(id);
        Assert.assertFalse(id.trim().isEmpty(), "id should not be blank");
    }

    @Test(description = "POST response should match create-user JSON schema")
    public void testCreateUserSchema() {
        User payload = new User("Schema Check", "Tester");

        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(payload)
        .when()
            .post(UserEndpoints.USERS)
        .then()
            .statusCode(201)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }

    @Test(description = "createdAt timestamp should be present and non-empty")
    public void testCreatedAtIsPresent() {
        User payload = new User("Timestamp Test", "Engineer");

        String createdAt = given()
            .spec(RequestSpecFactory.getRequestSpec())
            .body(payload)
        .when()
            .post(UserEndpoints.USERS)
        .then()
            .statusCode(201)
            .extract().jsonPath().getString("createdAt");

        Assert.assertNotNull(createdAt);
        Assert.assertFalse(createdAt.isEmpty());
    }
}