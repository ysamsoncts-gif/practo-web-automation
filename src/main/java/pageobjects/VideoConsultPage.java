package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import utilities.ScreenshotUtil;

public class VideoConsultPage {
    private WebDriver driver;
    private final CommonCode wait;
    private final ScreenshotUtil ss;

    @FindBy (xpath ="//div[text()='Skip the travel!']" )
    private WebElement bookVideoConsult;

    @FindBy(xpath = "(//a[@class=\"link primary-button cta\"])[1]")
    private WebElement consultBtn;

    @FindBy (xpath = "//textarea[@name=\"detailedDescription\"]")
    private WebElement symptomField;

    @FindBy (xpath = "//input[@type=\"tel\"]")
    private WebElement mobileNoLocator;

    @FindBy (xpath = "//label[@class=\"tag-label ng-binding\"] ")
    private WebElement specialityLocator;

    @FindBy (xpath = "//button[@class=\"btn-blue continue-btn\"]")
    private WebElement continueBtn;

    public VideoConsultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new CommonCode(driver, 20);
        PageFactory.initElements(driver, this);
    }
    public void navigateToVideoConsultPage() {
        bookVideoConsult.click();
    }
    public void consultNowBtnClick(){
        consultBtn.click();
    }


}
