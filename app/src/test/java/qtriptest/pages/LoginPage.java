package qtriptest.pages;
import java.util.UUID;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {
    private RemoteWebDriver driver;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text() = 'Login to QTrip']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='navbarNavDropdown']/ul/li[4]/div")
    private WebElement logoutButton;

    

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void performLogin(String username, String password) throws InterruptedException {
        // Use the provided username for login
         // Visit the login page URL
    driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/login/");
    
    // Use the provided username for login
    emailInput.sendKeys(username);
    passwordInput.sendKeys(password);
    loginButton.click();
    Thread.sleep(1000);
    System.out.println("User was able to login");
    }


    public boolean isLoggedin() throws InterruptedException {
        return logoutButton.isDisplayed();
        
    }

    public void performLogout() throws InterruptedException{
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
         WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='navbarNavDropdown']/ul/li[4]/div")));
        logoutButton.click();
        System.out.println("user was able to logout");
        
    }

    public boolean isLoggedOut() {
        // Check if the login button is displayed
        WebElement newloginButton = driver.findElement(By.xpath("//a[text() = 'Login Here']"));
        return newloginButton.isDisplayed();
    }
}

