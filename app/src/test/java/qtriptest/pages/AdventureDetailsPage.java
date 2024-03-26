package qtriptest.pages;

import java.util.concurrent.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AdventureDetailsPage {

    RemoteWebDriver driver;

    @FindBy(name = "name")
    WebElement nameInput;

    @FindBy(name = "date")
     WebElement dateSelect;

    @FindBy(xpath = "//input[@type='number']") 
     WebElement numberOfTicketsInput;
    
    @FindBy(id = "reserved-banner")
    WebElement reservedBanner;

    @FindBy(xpath = "//button[@class='reserve-button']")
    WebElement reserveBtn;

    public AdventureDetailsPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   

    public void enterName(String name) {
        nameInput.sendKeys(name);
        System.out.println("entered the" + name);
    }

    public void selectDate(String date) {
        dateSelect.sendKeys(date);
        System.out.println("selected the date" + date);
    }

    public void enterNumberOfTickets(int numberOfTickets) {
        if (numberOfTickets >= 1) {
            if (numberOfTickets > 10) {
                System.out.println("Input exceeds the maximum allowed value of 10. Setting to 10.");
                numberOfTickets = 10;
            }
            numberOfTicketsInput.clear();
            numberOfTicketsInput.sendKeys(String.valueOf(numberOfTickets));
            System.out.println("selected the no's" + numberOfTickets);
        } else {
            System.out.println("Invalid number of tickets. Please provide a value of 1 or greater.");
        }
    }

    public void clickOnReserveBtn(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(reserveBtn));
        reserveBtn.click();


    }

    public boolean isBookingSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(reservedBanner));
        return reservedBanner.isDisplayed() && reservedBanner.getText().contains("Greetings! Reservation for this adventure is successful");
    }
}
