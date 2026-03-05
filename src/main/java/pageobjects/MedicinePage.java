package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ScreenshotUtil;
import utilities.CommonCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MedicinePage {
    private final WebDriver driver;
    private final CommonCode cc;
    private final ScreenshotUtil ss;

    @FindBy(xpath = "//a[@title = 'tests']")
    private WebElement labTest;

    @FindBy(xpath = "//div[text() = \"Medicines\"]")
    private WebElement medicineBy;

    @FindBy(xpath = "(//img[@class = \"u-shape-wid--100\"])[1]")
    private WebElement skin;

    @FindBy (xpath = "//div[text() = \"Skin care\"]")
    private WebElement skinCare;

    @FindBy(xpath = "//div[text() = \"Face wash and cleansers\"]")
    private WebElement faceWash;

    @FindBy (xpath = "//div[@class=\"u-columns u-six\"]/button")
    private WebElement addBtn;

    @FindBy(xpath = "//input[@type = \"text\"]")
    private WebElement inputField;

    @FindBy(xpath = "//img[@alt='Cartisafe-d Tablet']")
    private WebElement cartisafeTablet;

    @FindBy(xpath = "//span[text() = \"ADD TO CART\"]")
    private WebElement addToCartTabletBtn;

    @FindBy(className="button__primary")
    private WebElement viewCartBtn;

    @FindBy (xpath = "//span[text() = \"Use my current location\"]")
    private WebElement currLocation;

    public MedicinePage(WebDriver driver) {
        this.driver = driver;
        this.cc = new CommonCode(driver, 20);
        PageFactory.initElements(driver, this);
        this.ss = new ScreenshotUtil();
    }

    public void navigateToLabTest()
    {
        labTest.click();
    }
    public void navigateToMedicine()
    {
        cc.clickable(medicineBy).click();
        ss.takeScreenshot(driver,"MedicinePage");
    }
    public void navigateToSkin()
    {
        cc.scrollIntoView(skin);
        skin.click();
    }
    public void addToCart(){
        Set<String>windowId = driver.getWindowHandles();
        List<String> windowList = new ArrayList(windowId);
        driver.switchTo().window(windowList.get(1));
        cc.hover(driver,skinCare);
        cc.hoverAndClick(driver,faceWash);
        addBtn.click();
        inputField.sendKeys("cartisafe");
        inputField.click();

        WebElement product = cc.visible(cartisafeTablet);
        cc.scrollIntoView(product);
        product.click();
        addToCartTabletBtn.click();

        cc.scrollIntoView(viewCartBtn);
        viewCartBtn.click();
        currLocation.click();
        ss.takeScreenshot(driver,"MedicineCart");
    }
}
