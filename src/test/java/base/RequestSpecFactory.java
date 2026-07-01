package base;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private static final Dotenv dotenv = Dotenv.load();

    private static final RequestSpecification REQUEST_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setContentType(ContentType.JSON)
                    .addHeader("x-api-key", dotenv.get("REQRES_API_KEY"))
                    .build();

    public static RequestSpecification getRequestSpec() {
        return REQUEST_SPEC;
    }
}