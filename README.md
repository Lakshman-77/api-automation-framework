# API Automation Framework

REST API test automation framework built with REST Assured and TestNG, targeting the [ReqRes](https://reqres.in) public API. Covers full CRUD operations with JSON schema validation and HTML reporting via ExtentReports.

## Tech Stack

- **Java 17**
- **REST Assured 5.4** — API testing DSL
- **TestNG 7.9** — test runner
- **Jackson Databind** — Java object ↔ JSON mapping
- **JSON Schema Validator** — contract/schema validation
- **ExtentReports** — HTML test reports
- **GitHub Actions** — CI on every push

## Project Structure

```
api-automation-framework/
├── src/test/
│   ├── java/
│   │   ├── base/BaseTest.java          # Base URI + logging filters
│   │   ├── endpoints/UserEndpoints.java # API endpoint constants
│   │   ├── models/User.java            # POJO for request/response
│   │   ├── listeners/                  # ExtentReports integration
│   │   └── tests/
│   │       ├── GetUserTest.java        # GET + schema validation
│   │       ├── CreateUserTest.java     # POST
│   │       ├── UpdateUserTest.java     # PUT + PATCH
│   │       └── DeleteUserTest.java     # DELETE
│   └── resources/
│       ├── testng.xml
│       └── schemas/
│           ├── get-user-schema.json
│           └── create-user-schema.json
├── .github/workflows/ci.yml
└── pom.xml
```

## Test Coverage (16 test cases)

| HTTP Method | Tests |
|---|---|
| GET | Single user, user not found (404), paginated list, schema validation, pagination correctness |
| POST | Create user, validate id, schema validation, createdAt timestamp |
| PUT | Full update, PATCH partial update, updatedAt timestamp, name-only PUT |
| DELETE | 204 status, empty response body, multiple user deletes |

## How to Run

**Prerequisites:** Java 17, Maven

```bash
git clone <repo-url>
cd api-automation-framework
mvn test
```

HTML report is generated at `test-output/api-report.html`.

## Design Decisions

- **Endpoint constants** in `UserEndpoints.java` so URL changes are a single-line fix
- **POJO model** (`User.java`) for cleaner request bodies instead of raw JSON strings
- **JSON Schema files** in `resources/schemas/` for contract testing — catches API response structure changes early
- **Request/Response logging** enabled in BaseTest so every CI failure shows the exact HTTP traffic
