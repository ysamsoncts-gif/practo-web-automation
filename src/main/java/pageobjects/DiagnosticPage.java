package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticPage {
    private WebDriver driver ;
    private final WaitUtils wait;

    public By bookDiagnosticTab = By.xpath("//span[text()='Book Diagnostic Tests']");
    public By topCities = By.xpath(" //div[@class=\"u-margint--standard o-f-color--primary\"]");

    public DiagnosticPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void navigateToDiagnosticPage(){
        WebElement diagnosticTestClick = driver.findElement(bookDiagnosticTab);
        diagnosticTestClick.click();
    }
    //wait for the top cities to visible
    public void waitForTopCities() {
        wait.visible(topCities);
    }

    //get all city names and return as a list
    public List<String> getTopCities() {
        List<WebElement> cityElements = driver.findElements(topCities);
        List<String> cities = new ArrayList<>();

        for (WebElement city : cityElements) {
            cities.add(city.getText());
        }
        return cities;
    }

    //print to console
    public void printTopCities(List<String> cities) {
        System.out.println("=====TOP CITIES=====");
        for (String city : cities) {
            System.out.println(city);
        }
    }

    public String GetPageTitle(){
        return driver.getTitle();
    }




}