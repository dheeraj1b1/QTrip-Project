// package qtriptest.tests;
// import qtriptest.pages.LoginPage;
// import qtriptest.pages.RegisterPage; 

// import java.net.MalformedURLException;
// import java.net.URL;

// import org.openqa.selenium.remote.BrowserType;
// import org.openqa.selenium.remote.DesiredCapabilities;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Test;
// import org.testng.asserts.Assertion;



// public class TestCases {

//     static RemoteWebDriver driver;

//     // Method to help us log our Unit Tests
//     public static void logStatus(String type, String message, String status) {
//         System.out.println(String.format("%s |  %s  |  %s | %s",
//                 String.valueOf(java.time.LocalDateTime.now()), type, message, status));
//     }

//     // Initialize webdriver for our Unit Tests
//     @BeforeClass(alwaysRun = true, enabled = true)
//     public static void createDriver() throws MalformedURLException {
//         logStatus("driver", "Initializing driver", "Started");
//         final DesiredCapabilities capabilities = new DesiredCapabilities();
//         capabilities.setBrowserName(BrowserType.CHROME);
//         driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
//         driver.manage().window().maximize();
//         logStatus("driver", "Initializing driver", "Success");
//     }

//     // Test for Navigate to Register Page
//     @Test(description = "Verify functionality of - navigate to register page", enabled = true)
//     public static void testNavigateToRegisterPage() {
//         Assertion assertion = new Assertion();
//         logStatus("Page test", "navigation to register page", "started");
//         try {
//             RegisterPage registerPage = new RegisterPage(driver);
//             registerPage.navigateToRegistrationPage();
//             String expectedUrl = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
//             String actualUrl = driver.getCurrentUrl();
//             assertion.assertEquals(actualUrl, expectedUrl, "Verify navigation to the registration page");
//             logStatus("Page test", "navigation to register page", "success");
//         } catch (Exception e) {
//             logStatus("Page test", "navigation to register page", "failed");
//             e.printStackTrace();
//         }
//     }

//     //  Test for Register New User
//     @Test(description = "Verify functionality of new user registration", enabled = true)
//     public static void testRegisterNewUser() {
//         Assertion assertion = new Assertion();
//         logStatus("Page test", "new user registration", "started");
//         try {
//             RegisterPage registerPage = new RegisterPage(driver);
//             String username = "chiinki@gmail.com";
//             String password = "nunni@12";
//             Boolean registrationStatus = registerPage.registerNewUser(username, password);
//             assertion.assertTrue(registrationStatus, "Verify new user registration");
//             logStatus("Page test", "new user registration", "success");
//         } catch (Exception e) {
//             logStatus("Page test", "new user registration", "failed");
//             e.printStackTrace();
//         }
//     }


//     @Test(description = "Verify functionality of user login & logout", enabled = true)
//     public static void testLoginUser() {
//         Assertion assertion = new Assertion();
//         logStatus("Page test", "user login", "started");
//         LoginPage loginPage = new LoginPage(driver);
        
//         try {
//             driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/login/");
//             String username = "cwjbc@gma.com"; // Use the same username as registered
//             String password = "@a123456"; // Use the same password as registered
//             loginPage.performLogin(username, password);
    
//             // Assuming you have logic to check if the user is logged in
//              boolean loginStatus = loginPage.isLoggedin();
//             assertion.assertTrue(loginStatus, "Verify user login");
//              logStatus("Page test", "user login", "success");
    
//            // Perform logout
//             loginPage.performLogout();
//              logStatus("Page test", "user logout", "success");
    
//             //  check if the user is logged out
//             boolean logoutStatus = loginPage.isLoggedOut();
//             assertion.assertTrue(logoutStatus, "Verify user logout");
//             logStatus("Page test", "user has logged-out", "success");
//         } catch (Exception e) {
//             logStatus("Page test", "user login/logout", "failed");
//             e.printStackTrace();
//         }
//     }
// }


