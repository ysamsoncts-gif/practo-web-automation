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
    public By hospitalRate = By.xpath("//div[@class='text-1']/child::span[@class='u-bold']");
    public By hospitalTime = By.xpath("//span[@class='pd-right-2px-text-green']");


    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void searchLocation(String location){
        Actions actions = new Actions(driver);
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

    public void getHospitalRating(){
        List <WebElement> hospitalRatings = driver.findElements(hospitalRate);
        for( WebElement rates:hospitalRatings ){
            System.out.println(rates.getText());
        }
    }

    public void getHospitalTiming(){
        List <WebElement> hospitalTimings = driver.findElements(hospitalTime);
        for(WebElement times:hospitalTimings){
            System.out.println(times.getText());
        }
    }

}
