# QTrip Automation — Selenium + TestNG + ExtentReports UI Test Suite

> A Selenium WebDriver automation framework built to test travel adventure booking workflows on the **QTrip Dynamic** platform. This project demonstrates data-driven testing, the Singleton design pattern, ExtentReports integration, and the Page Object Model — all practical skills used in professional QA roles.

---

## What This Project Demonstrates

| Skill | Details |
|---|---|
| **UI Automation** | End-to-end browser automation using Selenium WebDriver |
| **Reusable Framework Design** | POM + Singleton pattern for driver and report management |
| **Data-Driven Testing** | Test data loaded from an Excel file using Apache POI |
| **Reporting** | ExtentReports with screenshot capture on pass/fail |
| **Locator Strategy** | XPath and CSS selectors used across page objects |
| **Regression Testing** | Multiple independent test classes for isolated execution |

---

## Tech Stack

| Tool / Library | Version / Role |
|---|---|
| **Java** | Primary language |
| **Selenium WebDriver** | 4.0.0-alpha-1 — browser automation |
| **TestNG** | 7.6.0 — test runner, `@DataProvider`, groups |
| **ExtentReports** | 2.40.2 — rich HTML test reports with screenshots |
| **Apache POI** | 5.2.2 — reading test data from `.xlsx` Excel files |
| **Log4j** | 2.17.2 — structured test execution logging |
| **Gradle** | Build tool and test runner |

---

## Test Scenarios Automated

| Test Case | Description | Highlights |
|---|---|---|
| **TC01** | User Onboarding — register a new user, login, and logout | Data-driven via Excel, ExtentReports screenshot |
| **TC02** | Adventure Listing — search adventures by city and verify listing | Filtering logic, list validation |
| **TC03** | Adventure Booking — select adventure, apply filters, make reservation | Form interactions, dynamic dropdowns |
| **TC04** | Booking History — verify reservation appears in history | Navigation flow, data persistence check |

Note: `TestCases.java` contains a commented-out exploratory test suite used during initial development.

---

## Framework Structure

```
QTrip-Project/
├── app/
│   ├── OurExtentReport*.html                  # Sample Extent Report output (committed)
│   ├── extent_customization_configs.xml       # ExtentReports theme config
│   └── src/
│       └── test/
│           ├── java/
│           │   └── qtriptest/
│           │       ├── DP.java                # Data Provider — reads from Excel via Apache POI
│           │       ├── DriverSingleton.java   # Singleton: shared WebDriver instance
│           │       ├── ReportSingleton.java   # Singleton: shared ExtentReports instance
│           │       ├── SeleniumWrapper.java   # Utility wrapper for common Selenium actions
│           │       ├── testng.xml             # TestNG suite configuration
│           │       ├── pages/
│           │       │   ├── RegisterPage.java
│           │       │   ├── LoginPage.java
│           │       │   ├── HomePage.java
│           │       │   ├── AdventurePage.java
│           │       │   ├── AdventureDetailsPage.java
│           │       │   ├── Reservations.java
│           │       │   └── HistoryPage.java
│           │       └── tests/
│           │           ├── testCase_01.java   # User onboarding
│           │           ├── testCase_02.java   # Adventure listing
│           │           ├── testCase_03.java   # Booking flow
│           │           └── testCase_04.java   # Booking history
│           └── resources/
│               └── DatasetsforQTrip.xlsx      # Excel test data file
├── app/build.gradle                           # Gradle config & dependencies
├── settings.gradle
├── gradlew / gradlew.bat                      # Gradle wrapper
└── test-results.xml                           # TestNG result snapshot
```

---

## Key Automation Concepts Used

- **Page Object Model (POM)** — Each page has a dedicated class (RegisterPage, LoginPage, HomePage, etc.) encapsulating locators and actions
- **Singleton Pattern** — `DriverSingleton` and `ReportSingleton` manage shared WebDriver and ExtentReports instances across tests
- **Data-Driven Testing** — `DP.java` reads from `DatasetsforQTrip.xlsx` using Apache POI; each test case maps to a named sheet
- **ExtentReports Integration** — HTML reports include test steps, pass/fail status, and embedded screenshots
- **Screenshot Capture** — `ReportSingleton.captureAndAttachScreenshot()` captures screenshots on both pass and fail
- **TestNG DataProvider** — `@DataProvider` annotation connects test methods to Excel data rows
- **TestNG Lifecycle Annotations** — `@BeforeMethod`, `@AfterMethod`, `@AfterSuite` manage setup/teardown per test
- **Explicit Waits** — `WebDriverWait` with `ExpectedConditions` used for asynchronous element loading
- **Log4j Logging** — Execution logs capture test activity for debugging

---

## How to Run Tests

### Prerequisites

- Java 8+
- Gradle (or use the `./gradlew` wrapper)
- A Selenium Grid or Zalenium instance running at `http://localhost:8082/wd/hub`
- Test data file: `app/src/test/resources/DatasetsforQTrip.xlsx`

### Run All Tests

```bash
./gradlew test
```

### Run a Specific Test Case

Update `testng.xml` to include only the desired test class, then run:

```bash
./gradlew test
```

---

## Reporting

This project uses **ExtentReports** for visual HTML test reports.

- **Sample report:** `app/OurExtentReport*.html` — a generated report is committed for reference
- **Report config:** `app/extent_customization_configs.xml` controls theme and styling
- Reports include: test name, pass/fail status, step-by-step logs, and screenshots attached inline
- TestNG default listener output also available at: `build/reports/tests/test/index.html`

---

## Future Improvements

- [ ] Add **CI/CD pipeline** (GitHub Actions) to run tests on push/PR
- [ ] Upgrade Selenium to stable 4.x release
- [ ] Centralize test data config to avoid hardcoded sheet names in `DP.java`
- [ ] Add parallel test execution via `testng.xml` `parallel` attribute
- [ ] Implement retry logic for flaky tests using TestNG `IRetryAnalyzer`
- [ ] Add test coverage for negative booking scenarios

---

## GitHub Topics

`selenium` `java` `testng` `qa-automation` `automation-testing` `pom` `webdriver` `extentreports` `data-driven-testing` `apache-poi`
