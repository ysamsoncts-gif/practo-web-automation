package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ScreenshotUtil;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MedicinePage {
    private final WebDriver driver;
    private final WaitUtils wait;
    private final ScreenshotUtil ss;

    @FindBy(xpath = "//a[@title = 'tests']") private WebElement labTest;
    @FindBy(xpath = "//div[text() = \"Medicines\"]")private WebElement medicineBy;
    @FindBy(xpath = "(//img[@class = \"u-shape-wid--100\"])[1]") private WebElement skin;
    @FindBy (xpath = "//div[text() = \"Skin care\"]")private WebElement skinCare;
    @FindBy(xpath = "//div[text() = \"Face wash and cleansers\"]")private WebElement faceWash;
    @FindBy (xpath = "//div[@class=\"u-columns u-six\"]/button") private WebElement addButton;
    @FindBy(xpath = "//input[@type = \"text\"]")private WebElement inputField;
    @FindBy(xpath = "//img[@alt='Cartisafe-d Tablet']")private WebElement cartisafeTablet;
    @FindBy(xpath = "//span[text() = \"ADD TO CART\"]")private WebElement addToCartTablet;
    @FindBy(className="button__primary") private WebElement viewCartButton;
    @FindBy (xpath = "//span[text() = \"Use my current location\"]")private WebElement currLocation;

    public MedicinePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
        PageFactory.initElements(driver, this);
        this.ss = new ScreenshotUtil();
    }

    public void navigateToLabTest()
    {
        labTest.click();
    }
    public void navigateToMedicine()
    {
        wait.clickable(medicineBy).click();
        ss.takeScreenshot(driver,"MedicinePage");
    }
    public void navigateToSkin()
    {
        wait.scrollIntoView(skin);
        skin.click();
    }

    public void addToCart(){
        Set<String>windowId = driver.getWindowHandles();
        List<String> windowList = new ArrayList(windowId);
        driver.switchTo().window(windowList.get(1));
        new Actions(driver).moveToElement(skinCare).perform();
        new Actions(driver).moveToElement(faceWash).click().perform();
        addButton.click();
        inputField.sendKeys("cartisafe");
        inputField.click();

        WebElement product = wait.visible(cartisafeTablet);
        wait.scrollIntoView(product);
        product.click();
        addToCartTablet.click();

        wait.scrollIntoView(viewCartButton);
        viewCartButton.click();
        currLocation.click();
        ss.takeScreenshot(driver,"MedicineCart");

    }




}
