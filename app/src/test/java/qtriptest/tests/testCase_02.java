package qtriptest.tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;

import java.io.IOException;
import java.lang.reflect.Method;

public class testCase_02 {

    private static RemoteWebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setupTest(Method method) {
        // Initialize the Extent Report and start the test
        ReportSingleton.getExtentInstance();
        ReportSingleton.startTest(method.getName(), "Test case2");

        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        System.out.println("setupDriver()");
    }

    @DataProvider(name = "testData2")
    public Object[][] testData(Method method) throws IOException {
        return new qtriptest.DP().dpMethod2(method);
    }

    @Test(description = "Search for City and Verify Filters on Adventure Page", dataProvider = "testData2", priority = 2, groups = "Search and Filter flow")
    public void TestCase02(String cityName, String categoryFilter, String durationFilter, String expectedFilteredResultsStr, String expectedUnfilteredResultsStr, Method method) throws InterruptedException {
        try {
            // Convert String parameters to double
            double expectedFilteredResults = Double.parseDouble(expectedFilteredResultsStr);
            double expectedUnfilteredResults = Double.parseDouble(expectedUnfilteredResultsStr);

            // Create an instance of the HomePage
            HomePage homePage = new HomePage(driver);

            // Navigate to the Home Page
            homePage.navigateToHomePage();

            // Search for the city
            homePage.searchForCity(cityName);

            // Verify if the city is valid
            Assert.assertTrue(homePage.validCity(), "City link is not displayed for the valid city: " + cityName);

            // Click on the city link
            homePage.clickOnCity();

            // Create an instance of the AdventurePage
            AdventurePage adventurePage = new AdventurePage(driver);

            // Wait for the page
            Thread.sleep(500);

            // AdventurePage.waitForPageToLoad();
            // Apply Category and Duration Filters
            adventurePage.applyCategoryAndDurationFilters(categoryFilter, durationFilter);
            Thread.sleep(1000);

            // Verify the filtered results count
            int actualFilteredResults = adventurePage.getFilteredResultsCount();
            Assert.assertEquals(actualFilteredResults, expectedFilteredResults, "Filtered results count mismatch.");

            // Clear Filters and Category
            Thread.sleep(1000);
            adventurePage.clearFiltersAndCategory();
            Thread.sleep(1000);

            // Verify all records are displayed after clearing filters and category
            int actualAllRecordsCount = adventurePage.getAllResultsCount();
            Assert.assertEquals(actualAllRecordsCount, expectedUnfilteredResults, "All records count mismatch after clearing filters and category.");

            // Log a pass step and capture/attach screenshot
            ReportSingleton.logStep("pass", "Test Step Passed");
            ReportSingleton.captureAndAttachScreenshot(driver);

        } catch (Exception e) {
            // Log a fail step and capture/attach screenshot in case of failure
            ReportSingleton.logStep("fail", "Test Step Failed");
            ReportSingleton.captureAndAttachScreenshot(driver);
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public static void tearDownTest() {
        // End the test and flush the report
        ReportSingleton.endTest();
        ReportSingleton.flushReport();

        System.out.println("tearDownTest()");
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDownDriver() {
        DriverSingleton.quitDriver();
        System.out.println("tearDownDriver()");
    }
}