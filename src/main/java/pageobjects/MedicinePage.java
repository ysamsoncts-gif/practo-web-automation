package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MedicinePage {
    private final WebDriver driver;
    private final WaitUtils wait;
    @FindBy(xpath = "//a[@title = 'tests']") private WebElement labTest;
  //  @FindBy(xpath = "//div[text() = \"Medicines\"]") private WebElement medicine;
    private final By medicineBy = By.xpath("//div[text() = \"Medicines\"]");
    @FindBy(xpath = "(//img[@class = \"u-shape-wid--100\"])[1]") private WebElement skin;
    @FindBy (xpath = "//div[text() = \"Skin care\"]")private WebElement skinCare;
    @FindBy(xpath = "//div[text() = \"Face wash and cleansers\"]")private WebElement faceWash;
    @FindBy (xpath = "//div[@class=\"u-columns u-six\"]/button") private WebElement addButton;
    @FindBy(xpath = "//input[@type = \"text\"]")private WebElement inputField;
    private final By cartisafeTablet = By.xpath("//img[@alt=\"Cartisafe-d Tablet\"]");
    @FindBy(xpath = "//span[text() = \"ADD TO CART\"]")private WebElement addToCartTablet;
   // @FindBy(className = "icon-ic_cart") private WebElement viewCartButton;
    private final By viewCartButton = By.className("button__primary");
    @FindBy (xpath = "//span[text() = \"Use my current location\"]")private WebElement currLocation;
    public MedicinePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLabTest()
    {
        labTest.click();
    }
    public void navigateToMedicine()
    {
        wait.clickable(medicineBy).click();
    }
    public void navigateToSkin()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",skin);
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
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", product);
        product.click();
        addToCartTablet.click();
//        js.executeScript("window.scrollBy(0,200);");
        wait.scrollIntoView(driver.findElement(viewCartButton));
        wait.clickable(viewCartButton);
        driver.findElement(viewCartButton).click();
        currLocation.click();

    }




}
