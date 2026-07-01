# API Automation Framework

A reusable REST API automation framework built using **Java, REST Assured, TestNG, and Maven** for automated testing of the **ReqRes** public REST API. The framework follows a modular design with reusable request specifications, JSON schema validation, environment-based configuration, and CI integration.

---

## Features

- CRUD API automation (GET, POST, PUT, PATCH, DELETE)
- 23 automated API test cases
- Positive and negative test scenarios
- JSON Schema Validation
- Reusable Request Specification using `RequestSpecBuilder`
- POJO-based request payloads with Jackson
- Request & Response logging
- Environment variable-based API key management
- HTML reporting using ExtentReports
- GitHub Actions CI pipeline

---

## Tech Stack

- Java 17
- REST Assured 5.4
- TestNG 7.9
- Maven
- Jackson Databind
- JSON Schema Validator
- ExtentReports
- GitHub Actions

---

## Project Structure

```text
api-automation-framework/
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   ├── BaseTest.java
│       │   │   └── RequestSpecFactory.java
│       │   ├── endpoints/
│       │   │   └── UserEndpoints.java
│       │   ├── listeners/
│       │   │   └── ExtentReportListener.java
│       │   ├── models/
│       │   │   └── User.java
│       │   └── tests/
│       │       ├── CreateUserTest.java
│       │       ├── DeleteUserTest.java
│       │       ├── GetUserTest.java
│       │       ├── NegativeApiTest.java
│       │       └── UpdateUserTest.java
│       │
│       └── resources/
│           ├── schemas/
│           └── testng.xml
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## Test Coverage (23 Test Cases)

| Category | Coverage |
|----------|----------|
| GET APIs | Fetch single user, user list, pagination, schema validation, user not found |
| POST APIs | Create user, validate generated ID, schema validation, timestamp validation |
| PUT/PATCH APIs | Full update, partial update, timestamp validation, partial payload updates |
| DELETE APIs | Delete user, empty response validation, multiple delete scenarios |
| Negative Testing | Missing password, missing email, invalid login, invalid user ID, malformed requests |

---

## Design Decisions

- Centralized API endpoints using `UserEndpoints`
- Reusable `RequestSpecification` built with `RequestSpecBuilder`
- POJO-based request bodies using Jackson serialization
- JSON Schema validation for API contract testing
- Request and Response logging enabled for easier debugging
- Environment variable-based API key management using `.env`
- ExtentReports listener for HTML test reporting
- GitHub Actions workflow for Continuous Integration

---

## Running the Project

### Prerequisites

- Java 17+
- Maven 3.9+
- ReqRes API Key

### Clone Repository

```bash
git clone https://github.com/<your-username>/api-automation-framework.git
cd api-automation-framework
```

### Create a `.env` file

```text
REQRES_API_KEY=your_api_key_here
```

### Execute Tests

```bash
mvn clean test
```

---

## Test Report

After execution, the Extent Report is generated under:

```text
test-output/api-report.html
```

---

## Continuous Integration

The project includes a GitHub Actions workflow that automatically executes the API test suite on every push.

---

## Author

**Lakshman Naidu**

B.Tech CSE, IIT Dharwad