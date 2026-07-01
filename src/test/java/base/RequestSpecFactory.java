package base;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private static final String API_KEY;

    static {
        String envKey = System.getenv("REQRES_API_KEY");

        if (envKey != null && !envKey.isBlank()) {
            API_KEY = envKey;
        } else {
            API_KEY = Dotenv.load().get("REQRES_API_KEY");
        }
    }

    private static final RequestSpecification REQUEST_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setContentType(ContentType.JSON)
                    .addHeader("x-api-key", API_KEY)
                    .build();

    public static RequestSpecification getRequestSpec() {
        return REQUEST_SPEC;
    }
}