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

public class testCase_03 {

    private static RemoteWebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setupTest(Method method) {
        // Initialize the Extent Report and start the test
        ReportSingleton.getExtentInstance();
        ReportSingleton.startTest(method.getName(), "Test case3");

        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        System.out.println("setupDriver()");
    }

    @DataProvider(name = "testData3")
    public Object[][] testData(Method method) throws IOException {
        return new qtriptest.DP().dpMethod3(method);
    }

    @Test(dataProvider = "testData3", description = "Adventure Booking and Cancellation Flow", priority = 3, groups = "Booking and Cancellation Flow")
    public void TestCase03(String username, String password, String searchCity, String adventureName, String guestName, String date, String numberOfTickets, Method method) throws InterruptedException {
        try {
            // Create an instance of the RegisterPage
            RegisterPage registerPage = new RegisterPage(driver);

            // Navigate to the Registration Page & register a new user with dynamic username
            boolean registrationResult = registerPage.registerNewUser(username, password, true);

            // Create an instance of the LoginPage to verify landing on the login page
            LoginPage loginPage = new LoginPage(driver);

            // Perform login with the dynamic username
            loginPage.performLogin(registerPage.getRegisteredUsername(), password);

            // Assert that the user registration was successful if it lands on the login page
            Assert.assertTrue(registrationResult, "User registration was successful & able to log in.");

            // Create an instance of the HomePage
            HomePage homePage = new HomePage(driver);

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
            adventurePage.searchAndClickAdventure(adventureName);

            // Create an instance of the AdventureDetailsPage
            AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);

            // Enter Name, Date, and Number of Tickets
            adventureDetailsPage.enterName(guestName);
            adventureDetailsPage.selectDate(date);
            adventureDetailsPage.enterNumberOfTickets(Integer.parseInt(numberOfTickets));
            adventureDetailsPage.clickOnReserveBtn();

            // Verify that the adventure booking was successful
            Assert.assertTrue(adventureDetailsPage.isBookingSuccessful(), "Adventure booking was not successful.");

            // Create an instance of the Reservations page
            Reservations reservations = new Reservations(driver);

            // Click on the Reservation button
            reservations.clickOnReservationbtn();

            // Get the Transaction ID before cancellation
            String transactionIdBeforeCancellation = reservations.getTransactionIdNumber();

            // Cancel the Reservation
            reservations.cancelReservation();
            Thread.sleep(2000);

            // Refresh the page
            driver.navigate().refresh();
            Thread.sleep(2000);

            reservations.verifyCancelletation();

            // Verify that the page contains the message after cancellation
            Assert.assertTrue(reservations.isMessageDisplayed("Oops! You have not made any reservations yet!"), "Message not displayed after cancellation.");

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
