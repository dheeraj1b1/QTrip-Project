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
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import qtriptest.pages.Reservations;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class testCase_04 {

    private static RemoteWebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setupTest(Method method) {
        // Initialize the Extent Report and start the test
        ReportSingleton.getExtentInstance();
        ReportSingleton.startTest(method.getName(), "Test case4");

        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        System.out.println("setupDriver()");
    }

    @DataProvider(name = "testData4")
    public Object[][] testData(Method method) throws IOException {
        return new qtriptest.DP().dpMethod4(method);
    }

    @Test(dataProvider = "testData4", description = "Verify that Booking history can be viewed", priority = 4, groups = "Reliability Flow")
    public void TestCase04(String username, String password, String dataset1, String dataset2, String dataset3, Method method) throws InterruptedException {
        try {
            // Create an instance of the RegisterPage
            RegisterPage registerPage = new RegisterPage(driver);

            // Navigate to the Registration Page & register a new user with a dynamic username
            boolean registrationResult = registerPage.registerNewUser(username, password, true);

            // Create an instance of the LoginPage to verify landing on the login page
            LoginPage loginPage = new LoginPage(driver);

            // Perform login with the dynamic username
            loginPage.performLogin(registerPage.getRegisteredUsername(), password);

            // Assert that the user registration was successful if it lands on the login page
            Assert.assertTrue(registrationResult, "User registration was successful & able to log in.");

            // Create an instance of the HomePage
            HomePage homePage = new HomePage(driver);

            // Maintain a set to track visited cities
            Set<String> visitedCities = new HashSet<>();

            // Iterate over datasets
            for (String dataset : Arrays.asList(dataset1, dataset2, dataset3)) {
                // Split the dataset into individual values
                String[] values = dataset.split(";");
                // Track the current index
                int currentIndex = 0;

                // Extract values for the adventure
                String searchCity = values[currentIndex++];
                String adventureName = values[currentIndex++];
                String guestName = values[currentIndex++];
                String date = values[currentIndex++];
                String numberOfTickets = values[currentIndex++];

                // Check if the city has been visited, if not, proceed
                if (visitedCities.add(searchCity)) {
                    // Navigate to the Home Page
                    homePage.navigateToHomePage();

                    // Search for the city
                    homePage.searchForCity(searchCity);

                    // Verify if the city is valid
                    Assert.assertTrue(homePage.validCity(), "City link is not displayed for the valid city: " + searchCity);

                    // Click on the city link
                    homePage.clickOnCity();

                    // Create an instance of the AdventurePage
                    AdventurePage adventurePage = new AdventurePage(driver);

                    // Search and click on the adventure
                    Thread.sleep(500);
                    adventurePage.searchAndClickAdventure(adventureName);
                    Thread.sleep(500);

                    // Create an instance of the AdventureDetailsPage
                    AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);

                    // Enter Name, Date, and Number of Tickets
                    Thread.sleep(1000);
                    adventureDetailsPage.enterName(guestName);
                    adventureDetailsPage.selectDate(date);
                    adventureDetailsPage.enterNumberOfTickets(Integer.parseInt(numberOfTickets));
                    adventureDetailsPage.clickOnReserveBtn();
                    Thread.sleep(1000);

                    // Verify that the adventure booking was successful
                    Assert.assertTrue(adventureDetailsPage.isBookingSuccessful(), "Adventure booking was not successful.");

                    // Create an instance of the Reservations page
                    Reservations reservations = new Reservations(driver);

                    // Click on the Reservation button
                    Thread.sleep(1000);
                    boolean isReservationBtnClicked = reservations.clickOnReservationbtn();

                    // Assert that the button was clicked
                    Assert.assertTrue(isReservationBtnClicked, "Reservation button was not clicked successfully.");

                    // Get all the Transaction ID's
                    Thread.sleep(1000);
                    reservations.getAllTransactionIds();

                    // Log a pass step and capture/attach screenshot
                    ReportSingleton.logStep("pass", "Test Step Passed");
                    ReportSingleton.captureAndAttachScreenshot(driver);
                }
            }
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