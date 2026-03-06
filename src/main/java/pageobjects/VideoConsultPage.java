package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;

public class VideoConsultPage {
    private WebDriver driver;
    private final CommonCode wait;

    @FindBy (xpath ="//button[@class=\"btn btn-primary btn_small pos-abs\"]" )
    private WebElement bookVideoConsult;

    @FindBy(xpath = "(//a[@class=\"link primary-button cta\"])[1]")
    private WebElement consultBtn;

    @FindBy (xpath = "//textarea[@name=\"detailedDescription\"]")
    private WebElement symptomField;

    @FindBy (xpath = "(//label[@class=\"tag-label ng-binding\"])[2]")
    private WebElement specialityLocator;

    @FindBy (xpath = "//input[@type=\"tel\"]")
    private WebElement mobileNoLocator;

    @FindBy (xpath = "//button[@class=\"btn-blue continue-btn\"]")
    private WebElement continueBtn;

    @FindBy(xpath = "//p[@id=\"otpSentMsg\"]")
    private WebElement otpMsg;

    @FindBy (xpath = "//iframe[@id='login-iframe-form']")
    private WebElement frame;

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
    public void sendDetailsToForm(String speciality, String number){
        WebElement sendName = wait.visible(symptomField);
        sendName.sendKeys(speciality);
        WebElement sendPhNum = wait.visible(mobileNoLocator);
        sendPhNum.sendKeys(number);

    }
    public void specialityLocatorClick(){
        wait.clickable(specialityLocator).click();

    }
    public void continueBtnClick(){
        continueBtn.click();
    }
    public String getOtpMsg(){
        wait.visible(frame);
        driver.switchTo().frame(frame);
        return wait.visible(otpMsg).getText();
    }
}
