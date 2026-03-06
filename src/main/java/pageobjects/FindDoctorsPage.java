package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ScreenshotUtil;
import utilities.CommonCode;

import java.util.Set;

public class FindDoctorsPage {

    private final WebDriver driver;
    private final CommonCode cc;
    private final ScreenshotUtil ss;

    @FindBy (xpath = "(//div[@class='product-tab__title'])[1]")
    private WebElement productTabLocator;

    @FindBy (xpath = "//input[@data-qa-id='omni-searchbox-locality']")
    private WebElement findDoctorsLocationSearchBarLocator;

    @FindBy (xpath = "(//div[text()='Pune'])[1]")
    private WebElement puneOptionLocator;

    @FindBy (xpath = "//input[@data-qa-id='omni-searchbox-keyword']")
    private WebElement findDoctorsSearchDoctorsTabLocator;

    @FindBy (xpath = "//div[text()='Dentist']")
    private WebElement dentistOptionLocator;

    @FindBy (xpath = "//div[@data-qa-id='doctor_gender_section']")
    private WebElement genderBarLocator;

    @FindBy (xpath = "//li[@data-qa-id='female']")
    private WebElement femaleOptionLocator;

    @FindBy (xpath = "//div[@data-qa-id='doctor_review_count_section']")
    private WebElement reviewBarLocator;

    @FindBy (xpath = "(//li[@role='option'])[3]")
    private WebElement reviewFirstOptionLocator;

    @FindBy (xpath = "//div[@data-qa-id='years_of_experience_section']")
    private WebElement experienceBarLocator;

    @FindBy (xpath = "(//li[@role='option'])[6]")
    private WebElement experienceFirstOptionLocator;

    @FindBy (xpath = "//span[@data-qa-id='all_filters']")
    private WebElement allFilterBarLocator;

    @FindBy (xpath = "(//div[@data-qa-id='Availability_radio'])[3]")
    private WebElement tomorrowRadioLocator;

    @FindBy (xpath = "(//h2[@data-qa-id='doctor_name'])[1]")
    private WebElement doctorNameLocator;

    @FindBy (xpath = "//div[text()='tomorrow']")
    private WebElement tomorrowTabLocator;

    @FindBy (xpath = "//span[text()='12:00 PM']")
    private WebElement timeLocator;

    @FindBy (xpath = "//input[@placeholder='Mobile Number']")
    private WebElement mobileNumberBarLocator;

    @FindBy (xpath = "//button[text()='Continue']")
    private WebElement continueButtonLocator;

    @FindBy (xpath = "//div[@class='o-textbox__error']")
    private  WebElement errorTextLocator;

    public FindDoctorsPage(WebDriver driver) {
        this.driver = driver;
        this.cc = new CommonCode(driver, 20);
        this.ss = new ScreenshotUtil();
        PageFactory.initElements(driver, this);
    }

    public void navigateToFindDoctorsTab(){
        cc.clickable(productTabLocator).click();
    }

    public void findDoctorsTabSearchDoctors(){
       cc.visible(findDoctorsSearchDoctorsTabLocator).sendKeys("Dentist");
        cc.clickable(dentistOptionLocator).click();
    }

    public void findDoctorsTabSearchLocation(){
       cc.visible(findDoctorsLocationSearchBarLocator);
        findDoctorsLocationSearchBarLocator.clear();
        findDoctorsLocationSearchBarLocator.sendKeys("Pune");
        findDoctorsLocationSearchBarLocator.click();
        cc.clickable(puneOptionLocator).click();
    }

    public void selectGenderFilter(){
       cc.clickable(genderBarLocator).click();
       cc.clickable(femaleOptionLocator).click();
    }

    public void selectFeedbackFilter(){
        cc.clickable(reviewBarLocator).click();
        cc.clickable(reviewFirstOptionLocator).click();
    }

    public void selectExperienceFilter(){
        cc.clickable(experienceBarLocator).click();
        cc.clickable(experienceFirstOptionLocator).click();
    }

    public void selectALlFilters(){
        cc.clickable(allFilterBarLocator).click();
        cc.clickable(tomorrowRadioLocator).click();
    }

    public void navigateToDoctorBookingTab(){
        String originalWindow = driver.getWindowHandle();

        cc.visible(doctorNameLocator).click();

        Set<String> windowHandles = driver.getWindowHandles();

        String newWindow = null;

        for (String handle: windowHandles){
            if(!handle.equals(originalWindow)){
                newWindow = handle;
                break;
            }
        }
        driver.switchTo().window(newWindow);
    }

    public void selectBookingTime(){
        cc.visible(tomorrowTabLocator).click();
        timeLocator.click();
    }

    public void sendPhoneNumber(){
        Actions action = new Actions(driver);
        cc.visible(mobileNumberBarLocator).click();
        mobileNumberBarLocator.sendKeys("797776");
        action.click(continueButtonLocator).build().perform();
        cc.hoverAndClick(driver,continueButtonLocator);
        ss.takeScreenshot(driver,"Doctor_Booking_Form_Validation");
    }

    public String validateErrorMessage(){
       return cc.visible(errorTextLocator).getText();
    }
}
