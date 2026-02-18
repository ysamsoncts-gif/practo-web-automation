package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CorporatePage {


    private final WebDriver driver;

   public By name = By.id("name");
   public By orgName = By.id("organizationName");
   public By contactNumber = By.id("contactNumber");
   public By emailId = By.id("officialEmailId");
   public By orgSize = By.id("organizationSize");
   public  By interestedIn = By.id("interestedIn");

    public CorporatePage(WebDriver driver) {
        this.driver = driver;
    }





}
