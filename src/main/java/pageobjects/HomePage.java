package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtils;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;

    public By hospitalSearchBarLocator = By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']");
    public By hospitalOptionLocator = By.xpath("(//div[contains(text(),\"Hospital\")])[4]");

    public By locationSearchBarLocator = By.xpath("//input[@placeholder='Search location']");
    public By BangaloreOptionLocator = By.xpath("(//div[contains(text(),\"Bangalore\")])");
    public By JPNagarOptionLocator = By.xpath("(//div[contains(text(),\"Jp Nagar\")])");

    public By resultHospitalLocationLocator = By.xpath("//span[contains(text(),\"JP Nagar\" )]");

    public By resultHospitalNameLocator = By.xpath("//h2[@class='line-1']");
    public By resultHospitalRateLocator = By.xpath("//div[@class='text-1']/child::span[@class='u-bold']");
    public By resultHospitalTimeLocator = By.xpath("//span[@class='pd-right-2px-text-green']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void selectSearchHospitalClinic(String hospital) {
        WebElement sendHospital = driver.findElement(hospitalSearchBarLocator);
        sendHospital.click();
        sendHospital.sendKeys(hospital);
        wait.clickable(hospitalOptionLocator).click();
    }

    public void selectSearchLocation(String location)  {
        WebElement sendSearchLocation = driver.findElement(locationSearchBarLocator);
        sendSearchLocation.clear();
        sendSearchLocation.click();
        sendSearchLocation.sendKeys(location);
        wait.clickable(BangaloreOptionLocator).click();
    }

    public void selectSubSearchLocation(String location)  {
        WebElement sendSubLocation = driver.findElement(locationSearchBarLocator);
        sendSubLocation.clear();
        sendSubLocation.click();
        sendSubLocation.sendKeys(location);
        wait.clickable(JPNagarOptionLocator).click();
        wait.present(resultHospitalLocationLocator);
    }

    public boolean verifyHospitalName() {
        wait.present(resultHospitalLocationLocator);
        WebElement hospitalNameLocation = driver.findElement(resultHospitalLocationLocator);
        return hospitalNameLocation.getText().startsWith("JP Nagar");
    }

    public void storeHospitalName() {
        List<WebElement> hospitalNames = driver.findElements(resultHospitalNameLocator);
        for (WebElement names : hospitalNames) {
            System.out.println(names.getText());
        }
    }

    public void storeHospitalRating(){
        List <WebElement> hospitalRatings = driver.findElements(resultHospitalRateLocator);
        for( WebElement rates:hospitalRatings ){
            System.out.println(rates.getText());
        }
    }

    public void storeHospitalTiming(){
        List <WebElement> hospitalTimings = driver.findElements(resultHospitalTimeLocator);
        for(WebElement times:hospitalTimings){
            System.out.println(times.getText());
        }
    }

}
