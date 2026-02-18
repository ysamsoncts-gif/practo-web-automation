package pageobjects;

import org.openqa.selenium.*;
import utilities.WaitUtils;


public class SurgeriesPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By desktopSurgeries = By.xpath("//a[@title=\"surgery\" and @event=\"Nav Drawer:Interacted:Surgery\"]");

    public SurgeriesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20); // keep your 20s explicit wait
    }

    public void navigateToSurgeriesPage() {
        WebElement link = driver.findElement(desktopSurgeries);
        try {
            link.click();
        } catch (WebDriverException e) {
            System.out.println("Falling to js ");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }
}