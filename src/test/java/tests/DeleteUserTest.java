package tests;

import base.BaseTest;
import base.RequestSpecFactory;
import endpoints.UserEndpoints;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {

    @Test(description = "DELETE existing user should return 204 No Content")
    public void testDeleteUser() {
        given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 2)
        .when()
            .delete(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(204);
    }

    @Test(description = "DELETE response body must be empty (no content)")
    public void testDeleteResponseBodyIsEmpty() {
        String responseBody = given()
            .spec(RequestSpecFactory.getRequestSpec())
            .pathParam("id", 3)
        .when()
            .delete(UserEndpoints.USER_BY_ID)
        .then()
            .statusCode(204)
            .extract().body().asString();

        Assert.assertTrue(responseBody.isEmpty(), "204 response should have no body");
    }

    @Test(description = "DELETE on different user ids should consistently return 204")
    public void testDeleteMultipleUsers() {
        int[] userIds = {4, 5, 6};

        for (int id : userIds) {
            given()
                .spec(RequestSpecFactory.getRequestSpec())
                .pathParam("id", id)
            .when()
                .delete(UserEndpoints.USER_BY_ID)
            .then()
                .statusCode(204);
        }
    }
}