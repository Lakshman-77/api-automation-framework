# API Automation Framework

[![API Automation Tests](https://github.com/Lakshman-77/api-automation-framework/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/Lakshman-77/api-automation-framework/actions/workflows/ci.yml)

A reusable REST API automation framework built using **Java, REST Assured, TestNG, and Maven** for testing the **ReqRes** public REST API. The framework demonstrates scalable API test automation practices including reusable request specifications, centralized endpoint management, JSON schema validation, secure configuration management, HTML reporting, and GitHub Actions CI.

---

## Features

- Automated CRUD and negative API test suite (23 test cases)
- Reusable `RequestSpecification` using `RequestSpecBuilder`
- JSON Schema Validation for API contract testing
- POJO-based request payloads with Jackson
- Request & Response logging for debugging
- Secure API key management using environment variables
- HTML reporting with ExtentReports
- GitHub Actions Continuous Integration

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
│           │   ├── create-user-schema.json
│           │   └── get-user-schema.json
│           └── testng.xml
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## Test Coverage

| Module | Coverage |
|---------|----------|
| GET | Single user, pagination, schema validation, user not found |
| POST | User creation, generated ID validation, schema validation, timestamp validation |
| PUT / PATCH | Full update, partial update, timestamp validation |
| DELETE | Delete operations, empty response validation, multiple delete scenarios |
| Negative Testing | Missing password, missing email, invalid login, invalid user ID, malformed requests |

---

## Design Decisions

- Centralized API endpoints using `UserEndpoints`
- Reusable `RequestSpecification` built with `RequestSpecBuilder`
- POJO-based request payloads using Jackson serialization
- JSON Schema validation for API contract testing
- Request and Response logging for easier debugging
- Environment variable-based API key management
- ExtentReports integration for HTML test reporting
- GitHub Actions workflow for Continuous Integration

---

## Running the Project

### Prerequisites

- Java 17+
- Maven 3.9+
- ReqRes API Key

### Clone the Repository

```bash
git clone https://github.com/Lakshman-77/api-automation-framework.git
cd api-automation-framework
```

### Environment Configuration

For local development, create a `.env` file in the project root:

```text
REQRES_API_KEY=your_api_key_here
```

For GitHub Actions, configure the API key as a **Repository Secret**:

```text
Settings → Secrets and variables → Actions
```

Secret name:

```text
REQRES_API_KEY
```

### Execute Tests

```bash
mvn clean test
```

---

## Test Report

After execution, the Extent Report is generated at:

```text
test-output/api-report.html
```

---

## Continuous Integration

The GitHub Actions workflow automatically builds the project and executes the complete API test suite on every push and pull request.

---

## Author

**Lakshman Naidu**

B.Tech in Computer Science and Engineering  
Indian Institute of Technology Dharwad