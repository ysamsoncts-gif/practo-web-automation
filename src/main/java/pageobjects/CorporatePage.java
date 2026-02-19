package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.WaitUtils;

public class CorporatePage {


    private final WebDriver driver;
    private final WaitUtils wait;

   public By name = By.id("name");
   public By orgName = By.id("organizationName");
   public By contactNumber = By.id("contactNumber");
   public By emailId = By.id("officialEmailId");
   public By orgSize = By.id("organizationSize");
   public  By interestedIn = By.id("interestedIn");
   public By submit = By.xpath("(//button[@type = \"submit\"])[2]");
    public By navCorporateTab = By.xpath("//span[@event='Nav Provider Marketing:Interacted:Plus Corporate']");
    public By healthWellnessTab = By.xpath("//a[@event='Nav Provider Marketing:Interacted:Plus Corporate']");

    public CorporatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void navigateToCorporateTab(){
        WebElement corporateClick = driver.findElement(navCorporateTab);
        corporateClick.click();
    }

    public void navigateToHealthWellnessPage(){
        WebElement healthWellnessClick = driver.findElement(healthWellnessTab);
        healthWellnessClick.click();
    }


     public void validatingData(String namee, String organizationNamee, String contactNumberr, String emailIdd, int n, int m)
    {
        WebElement nameField = driver.findElement(name);
        nameField.sendKeys(namee);
        WebElement orgNameField = driver.findElement(orgName);
        orgNameField.sendKeys(organizationNamee);
        WebElement contactField = driver.findElement(contactNumber);
        contactField.sendKeys(contactNumberr);
        WebElement emailField = driver.findElement(emailId);
        emailField.sendKeys(emailIdd);

        WebElement orgSizeField = driver.findElement(orgSize);
        Select select1 = new Select(orgSizeField);
        select1.selectByIndex(n);

        WebElement submitButton = driver.findElement(submit);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);",submitButton);

        js.executeScript("window.scrollBy(0, 300);");
        WebElement interestedInField = driver.findElement(interestedIn);
        Select select2 = new Select(interestedInField);
        select2.selectByIndex(m);


    }






}
