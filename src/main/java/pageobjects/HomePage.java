package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;

    public By locationSearchBar = By.xpath("//input[@placeholder='Search location']");
    public By searchHospital = By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']");
    public By hospitalName = By.xpath("//h2[@class='line-1']");


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void searchLocation(String location){
        WebElement sendLocation = driver.findElement(locationSearchBar);
        sendLocation.clear();
        sendLocation.sendKeys(location);
    }

    public void searchHospitalClinic(String hospital) {
        Actions actions = new Actions(driver);
        WebElement sendHospital = driver.findElement(searchHospital);
        sendHospital.sendKeys(hospital);
        actions.click(sendHospital)
                .pause(Duration.ofMillis(2000))
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .sendKeys(Keys.RETURN)
                .build()
                .perform();

    }

    public void getHospitalName(){
        List <WebElement> hospitalNames = driver.findElements(hospitalName);
        for(WebElement names:hospitalNames){
            System.out.println(names.getText());
        }
    }


}
