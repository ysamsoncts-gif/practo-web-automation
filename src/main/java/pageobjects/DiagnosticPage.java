package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticPage {
    private WebDriver driver;
    private final WaitUtils wait;

    public By bookDiagnosticTab = By.xpath("//span[text()='Book Diagnostic Tests']");
    public By topCities = By.xpath(" //div[@class=\"u-margint--standard o-f-color--primary\"]");
    public By citySearchBox = By.xpath("//input[@placeholder=\"Search for city\"]");
    public By cityOption = By.xpath("(//div[text()='Pune'])[1]");
    public By packageName = By.xpath("//h3[@data-aid=\"popular-health-packages-card-title\"]");
    public By scrollBtn = By.xpath("(//div[@class='slick-arrow-wrapper'])[2]");

    public By packageAgeGroup  = By.xpath("//p[@class='c-package__age-text']");
    public By packagePrice     = By.xpath("//span[@class='c-package__price-current']");


    public DiagnosticPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void navigateToDiagnosticPage() {
        WebElement diagnosticTestClick = driver.findElement(bookDiagnosticTab);
        diagnosticTestClick.click();
    }
    public String GetPageTitle() {
        return driver.getTitle();
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



    public void searchBox(String city) {
        Actions action = new Actions(driver);
        WebElement search = wait.visible(citySearchBox);
        search.clear();
        search.sendKeys(city);
        search.click();

    }

    public void chooseCity(String city) {
        wait.clickable(cityOption).click();
    }


//    public List<WebElement> getPackageNames() {
//        return driver.findElements(packageName);
//    }
        public void clickScroll(){
        Actions action = new Actions(driver);
        }
//    public void storePackageNames() {
//        List <WebElement> packageNames = driver.findElements(packageName);
//        for(WebElement names:packageNames){
//            System.out.println(names.getText());
//        }
//    }
public void printPackageDetails() {
    List<WebElement> names  = driver.findElements(packageName);
    List<WebElement> ages   = driver.findElements(packageAgeGroup);
    List<WebElement> prices = driver.findElements(packagePrice);

    System.out.println("======= POPULAR HEALTH PACKAGES =======");

    for (int i = 0; i < names.size(); i++) {
        String name  = names.get(i).getText().trim();
        if (name.isEmpty()) continue; // <<< prevents the blank lines

        String age   = (i < ages.size())   ? ages.get(i).getText().trim()   : "";
        String price = (i < prices.size()) ? prices.get(i).getText().trim() : "";

        System.out.println(name + " | " + (age.isEmpty() ? "N/A" : age) + " | " + (price.isEmpty() ? "N/A" : price));
    }
}

//
//    public List<WebElement> getPackagePrices() {
//        return driver.findElements(packagePrice);
//    }
//
//    public List<WebElement> getPackageAgeGroups() {
//        return driver.findElements(packageAgeGroup);
//    }

}
