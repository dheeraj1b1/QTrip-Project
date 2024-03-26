
package qtriptest.tests;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qtriptest.pages.RegisterPage;
import qtriptest.pages.LoginPage;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;

import java.io.IOException;
import java.lang.reflect.Method;

public class testCase_01 {
    
    private static RemoteWebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setupTest(Method method)  {
        // Initialize the Extent Report and start the test
        ReportSingleton.getExtentInstance();
        ReportSingleton.startTest(method.getName(), "Test case1");

        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        System.out.println("setupDriver()");
    }

    @DataProvider(name = "testData")
    public Object[][] testData(Method method) throws IOException {
        return new qtriptest.DP().dpMethod(method);
    }

    @Test(description = "User Onboarding flow", dataProvider = "testData", priority = 1, groups ="Login Flow")
    public void TestCase01(String username, String password, Method method) throws InterruptedException {
        try {
            // Create an instance of the RegisterPage
            RegisterPage registerPage = new RegisterPage(driver);

            // Navigate to the Registration Page & register a new user with a dynamic username
            boolean registrationResult = registerPage.registerNewUser(username, password, true);

            // Create an instance of the LoginPage to verify landing on the login page
            LoginPage loginPage = new LoginPage(driver);

            // Perform login with the dynamic username
            loginPage.performLogin(registerPage.getRegisteredUsername(), password);

            // Verify if registration was successful by checking if it lands on the login page
            boolean isOnLoginPage = loginPage.isLoggedin();

            // Assert that the user registration was successful if it lands on the login page
            Assert.assertTrue(registrationResult && isOnLoginPage, "User registration was successful & able to log in.");

            // Perform log-out
            loginPage.performLogout();
            boolean haveLoggedOut = loginPage.isLoggedOut();

            // Verify that the user logged out
            Assert.assertTrue(haveLoggedOut, "User was able to log out of the application.");

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