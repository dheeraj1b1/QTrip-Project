package qtriptest.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    @FindBy(className = "hero-input")
    private WebElement searchBar;

    @FindBy(id = "results")
    private WebElement resultsContainer;

    @FindBy(xpath = "//ul[@id='results']//li")
    private WebElement cityElement;

    @FindBy(xpath = "//ul[@id='results']//a")
    private WebElement cityLink;
   
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        System.out.println("navigated to home page");
    }

    public void searchForCity(String cityName) throws InterruptedException {
        searchBar.clear();
        Thread.sleep(3000);
        searchBar.sendKeys(cityName);
        Thread.sleep(3000);
        System.out.println("searched for" + cityName );
    }

    public boolean invalidCity() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String expectedText = "No City found";  
        return wait.until(ExpectedConditions.textToBePresentInElement(resultsContainer, expectedText));
    }

    public boolean validCity() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By cityLink = By.xpath("//ul[@id='results']//a");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cityLink)).isDisplayed();
    }

    public void clickOnCity() throws InterruptedException {
        By cityElement = By.xpath("//ul[@id='results']//a");
        Thread.sleep(1000);
        driver.findElement(cityElement).click();
        //System.out.println("clciked on the city" + cityName);
       
    }
}

