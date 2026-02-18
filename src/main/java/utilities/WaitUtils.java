package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
        private final WebDriver driver;
        private final WebDriverWait wait;

        public WaitUtils(WebDriver driver, int seconds) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        }

        public WebElement visible(By locator) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public WebElement clickable(By locator) {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

        public boolean present(By locator) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public boolean visibleSafe(By locator) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

}
