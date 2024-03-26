package qtriptest.pages;

import org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdventurePage {
     
        private WebDriver driver;

    @FindBy(xpath = "//select[@onchange='selectDuration(event)']")
    private WebElement durationFilter;

    @FindBy(xpath = "//select[@onchange='selectCategory(event)']")
    private WebElement categoryFilter;

    //@FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    //private List<WebElement> adventureItems;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    private WebElement clearDurationButton;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    private WebElement clearCategoryButton;

    @FindBy(id = "search-adventures")
    private WebElement searchAdventureBtn;

    @FindBy(className = "activity-card")
    private WebElement firstActivityCard;


    public AdventurePage(WebDriver driver) {
        this.driver = driver;
    }

    public void applyCategoryAndDurationFilters(String categoryValue, String durationValue) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Select category filter
        System.out.println("Selecting category filter: " + categoryValue);
        // Thread.sleep(2000);
        // WebElement categoryFilterElement = wait.until(ExpectedConditions.elementToBeClickable(categoryFilter));
        WebElement filterHour = driver.findElement(By.id("duration-select"));
        Thread.sleep(2000);
        filterHour.click();
        Select dropdown = new Select(filterHour);
        dropdown.selectByVisibleText(durationValue);
        System.out.println("Selected duration filter: " + categoryValue);
        
        WebElement filterCategory = driver.findElement(By.id("category-select"));
        Thread.sleep(2000);
        filterCategory.click();
        Select dropdown2 = new Select(filterCategory);
        dropdown2.selectByVisibleText(categoryValue); 
        System.out.println("Selected category filter: " + categoryValue);       
        

        // // Use Select class for interacting with dropdowns
        // Select categorySelect = new Select(categoryFilter);
        // categorySelect.selectByVisibleText(categoryValue);
        // System.out.println("Category filter selected: " + categoryValue);

        // // Select duration filter
        // System.out.println("Selecting duration filter: " + durationValue);
        // Thread.sleep(2000);
        // WebElement durationFilterElement = wait.until(ExpectedConditions.elementToBeClickable(durationFilter));
        // //Thread.sleep(1000);
        

        // // Use Select class for interacting with dropdowns
        // Select durationSelect = new Select(durationFilter);
        // durationSelect.selectByVisibleText(durationValue);
        // System.out.println("Duration filter selected: " + durationValue);
    }

    public int getFilteredResultsCount() {
         // Retrieve result count after both filters are applied
         List<WebElement> adventureItems = driver.findElements(By.className("activity-card"));
         return adventureItems.size();
    }

    public void clearFiltersAndCategory() throws InterruptedException {
        System.out.println("Clearing duration and category filters");
        WebElement clearDurationBtn = driver.findElement(By.xpath("//div[@onclick = 'clearDuration(event)']"));
        clearDurationBtn.click();

        WebElement clearCategoryBtn = driver.findElement(By.xpath("//div[@onclick = 'clearCategory(event)']"));
        clearCategoryBtn.click();
        System.out.println("Filters cleared");
    }


    public int getAllResultsCount() {
        // Retrieve result count after filters are cleared
        List<WebElement> adventureItems = driver.findElements(By.className("activity-card"));
         return  adventureItems.size();
    }

    public void searchAndClickAdventure(String adventureName) throws InterruptedException {
        // Input adventureName into the search box
        Thread.sleep(1000);
        WebElement searchAdventure = driver.findElement(By.id("search-adventures"));
         searchAdventure.sendKeys(adventureName);
        Thread.sleep(1000);
        WebElement activityCard = driver.findElement(By.className("activity-card"));
        Thread.sleep(1000);
        activityCard.click();
        Thread.sleep(1000);
        System.out.println("searched and clicked on" + adventureName);




        // Click on the first adventure card if found
        // if (firstActivityCard != null) {
        //     firstActivityCard.click();
        // } else {
            
        //     System.out.println("No adventure card found for the adventure: " + adventureName);
        // }
    }

   
}