package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonCode {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public CommonCode(WebDriver driver, int seconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    public WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement clickable(By locator) {
        if (locator == null) {
            throw new IllegalArgumentException("locator must not be null");
        }
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement clickable(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public boolean present(By locator) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    public void scrollIntoView(WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        } catch (Exception ignored) {}
    }

    public void safeClick(WebElement el) {
        try {
            el.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }
    public WebElement visible(WebElement locator) {
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }
    public String getText(WebElement element)
    {
        return element.getText();
    }
    public boolean isElementDisplayed(WebElement ele){
            return ele.isDisplayed();
    };
    public void hover(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }
    public void hoverAndClick(WebDriver driver, WebElement hoverTarget) {
        new Actions(driver)
                .moveToElement(hoverTarget)
                .click()
                .perform();
    }

}
