
package qtriptest.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Reservations {

     RemoteWebDriver driver;

    @FindBy(xpath = "//table[@class='table']//tbody[@id='reservation-table']/tr[1]/th")
     WebElement transactionIdElement;

    @FindBy(className = "cancel-button")
     WebElement cancelButton;

    @FindBy(id = "no-reservation-banner")
     WebElement noReservationBanner;

     @FindBy(xpath  = "//a[text()='Reservations']")
     WebElement Reservationbtn;


    public Reservations(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean clickOnReservationbtn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(Reservationbtn));
            Reservationbtn.click();
            return true; // Button clicked successfully
        } catch (Exception e) {
            System.out.println("Unable to click on the Reservation button.");
            return false; // Button click failed
        }
    }

    public String getTransactionIdNumber() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(transactionIdElement));
        
        String transactionId = transactionIdElement.getText();
        System.out.println("Transaction ID: " + transactionId);
        
        return transactionId;
    }


    public void cancelReservation() {
        

        // Click on the Cancel button
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
       
    }


     public void verifyCancelletation (){
        
        // Refresh the page
        //driver.navigate().refresh();

        // Wait for the message after cancellation
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noReservationBanner));

        // Verify that the page contains the message after cancellation
        Assert.assertTrue(isMessageDisplayed("Oops! You have not made any reservations yet!"), "Message not displayed after cancellation.");

         }

    public boolean isMessageDisplayed(String message) {
        // Check if the page contains the specified message
        return driver.getPageSource().contains(message);
    }


    public List<String> getAllTransactionIds() {
        List<String> transactionIds = new ArrayList<>();
    
        // Find all Transaction ID elements
        List<WebElement> transactionIdElements = driver.findElements(By.xpath("//table[@class='table']//tbody[@id='reservation-table']//th[@scope='row']"));
    
        for (WebElement element : transactionIdElements) {
            // Get the Transaction ID text
            String transactionId = element.getText();
    
            // Assuming the Transaction ID is not empty
            if (!transactionId.isEmpty()) {
                transactionIds.add(transactionId);
            }
        }
    
        // Print all Transaction IDs
        System.out.println("All Transaction IDs: " + transactionIds);
    
        // Return the collected Transaction IDs
        return transactionIds;
    }

    
}
  


   


