package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.WaitUtils;

import java.util.Set;

public class FindDoctorsPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    public By productTabLocator = By.xpath("(//div[@class='product-tab__title'])[1]");

    public By findDoctorsLocationSearchBarLocator = By.xpath("//input[@data-qa-id='omni-searchbox-locality']");
    public By puneOptionLocator = By.xpath("(//div[text()='Pune'])[1]");

    public By findDoctorsSearchDoctorsTabLocator = By.xpath("//input[@data-qa-id='omni-searchbox-keyword']");
    public By dentistOptionLocator = By.xpath("//div[text()='Dentist']");

    public By genderBarLocator = By.xpath("//div[@data-qa-id='doctor_gender_section']");
    public By femaleOptionLocator = By.xpath("//li[@data-qa-id='female']");

    public By reviewBarLocator = By.xpath("//div[@data-qa-id='doctor_review_count_section']");
    public By reviewFirstOptionLocator = By.xpath("(//li[@role='option'])[3]");

    public By experienceBarLocator = By.xpath("//div[@data-qa-id='years_of_experience_section']");
    public By experienceFirstOptionLocator = By.xpath("(//li[@role='option'])[6]");

    public By allFilterBarLocator = By.xpath("//span[@data-qa-id='all_filters']");
    public By tomorrowRadioLocator = By.xpath("(//div[@data-qa-id='Availability_radio'])[3]");

    public By doctorNameLocator = By.xpath("(//h2[@data-qa-id='doctor_name'])[1]");

    public By tomorrowTabLocator = By.xpath("//div[text()='tomorrow']");
    public By timeLocator = By.xpath("//span[text()='12:00 PM']");

    public By mobileNumberBarLocator = By.xpath("//input[@placeholder='Mobile Number']");
    public By continueButtonLocator = By.xpath("//button[text()='Continue']");
    public By errorTextLocator = By.xpath("//div[@class='o-textbox__error']");




    public FindDoctorsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void navigateToFindDoctorsTab(){
        wait.clickable(productTabLocator).click();
    }

    public void findDoctorsTabSearchDoctors(){
        WebElement searchDoctor = wait.visible(findDoctorsSearchDoctorsTabLocator);
        searchDoctor.sendKeys("Dentist");
        wait.clickable(dentistOptionLocator).click();
    }

    public void findDoctorsTabSearchLocation(){
        WebElement searchLocation = wait.visible(findDoctorsLocationSearchBarLocator);
        searchLocation.clear();
        searchLocation.sendKeys("Pune");
        searchLocation.click();
        wait.clickable(puneOptionLocator).click();
    }

    public void selectGenderFilter(){
        WebElement clickGenderTab = wait.clickable(genderBarLocator);
        clickGenderTab.click();
        WebElement clickFemaleOption = wait.clickable(femaleOptionLocator);
        clickFemaleOption.click();
    }

    public void selectFeedbackFilter(){
        WebElement clickReviewBar= wait.clickable(reviewBarLocator);
        clickReviewBar.click();
        WebElement clickFirstOption = wait.clickable(reviewFirstOptionLocator);
        clickFirstOption.click();
    }

    public void selectExperienceFilter(){
        WebElement clickExperienceBar = wait.clickable(experienceBarLocator);
        clickExperienceBar.click();
        WebElement clickFirstOption = wait.clickable(experienceFirstOptionLocator);
        clickFirstOption.click();
    }

    public void selectALlFilters(){
        WebElement clickAllFilters = wait.clickable(allFilterBarLocator);
        clickAllFilters.click();
        WebElement clickRadio = wait.clickable(tomorrowRadioLocator);
        clickRadio.click();
    }

    public void navigateToDoctorBookingTab(){
        String originalWindow = driver.getWindowHandle();

        WebElement clickDoctorName = wait.visible(doctorNameLocator);
        clickDoctorName.click();

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
        WebElement clickTomorrow = wait.visible(tomorrowTabLocator);
        clickTomorrow.click();
        WebElement clickTime = driver.findElement(timeLocator);
        clickTime.click();
    }

    public void sendPhoneNumber(){
        Actions action = new Actions(driver);
        WebElement clickMobileInput = wait.visible(mobileNumberBarLocator);
        clickMobileInput.click();
        clickMobileInput.sendKeys("797776");
        WebElement continueButton = driver.findElement(continueButtonLocator);
        action.click(continueButton).build().perform();
    }

    public String validateErrorMessage(){
        WebElement errorMessage = wait.visible(errorTextLocator);
        return  errorMessage.getText();
    }
}
