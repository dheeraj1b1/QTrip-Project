package qtriptest.pages;
import java.util.UUID;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    String homeUrl ="https://qtripdynamic-qa-frontend.vercel.app/";
    String Rurl = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
     private String registeredUsername;

    private RemoteWebDriver driver;

    @FindBy(xpath = "//button[@class = 'navbar-toggler collapsed']")
    private WebElement navBtn;

    @FindBy(xpath = "//a[text() = 'Register']")
    private WebElement registerBtn;

    @FindBy(name = "email")
    private WebElement Username;

    @FindBy(name = "password")
    private WebElement Password;

    @FindBy(name  = "confirmpassword")
    private WebElement confirmPassword;

    @FindBy(xpath  = "//button[text() = 'Register Now']")
    private WebElement registerButton;

    public RegisterPage (RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToRegistrationPage() throws InterruptedException  {
        String currentUrl = driver.getCurrentUrl();

    if (currentUrl.equals(Rurl)) {
        System.out.println("Already on the registration page.");
        
    } else {
        // Maximize the window
     driver.get(Rurl);

        // Verify that the registration page is displayed
        WebElement registrationHeader = driver.findElement(By.xpath("//h2[text() = 'Register']"));
        String registrationHeaderText = registrationHeader.getText();
        if (registrationHeaderText.equals("Register")) {
            System.out.println("Navigated to the registration page.");
        } else {
            System.out.println("Registration page is not displayed.");
        }
    }
    }
    


    public String makeUsernameDynamic(String username) {
        // Append a random UUID to the provided username
        return  String.format("testuser_%s@email.com", UUID.randomUUID());
    }
    
    public Boolean registerNewUser(String username, String password, boolean generateDynamicUsername) throws InterruptedException {
        // Navigate to the registration page if not already on it
        navigateToRegistrationPage();

        // Use the provided username or generate a dynamic one
        String finalUsername = generateDynamicUsername ? makeUsernameDynamic(username) : username;

        // Fill in the registration form
        Username.sendKeys(finalUsername);
        Password.sendKeys(password);
        confirmPassword.sendKeys(password);
        registerButton.click();
        Thread.sleep(2500);

        // Store the registered username (email)
        registeredUsername = finalUsername;

        // Validate that the user has registered & landed on the login page
        WebElement loginHeader = driver.findElement(By.xpath("//h2[text() = 'Login']"));

        if (loginHeader.isDisplayed()) {
            System.out.println("User registered successfully and landed on the login page.");
            return true;
        } else {
            System.out.println("User registration failed or did not land on the login page.");
            return false;
        }
    }
    public String getRegisteredUsername() {
        return registeredUsername;
    }
    


    
    
}
