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

    public By loginSignupButtonLocator = By.xpath("//a[text()='Login / Signup']");

    public By mobileNoEmailInputLocator = By.xpath("//input[@id='username']");
    public By passwordInputLocator = By.xpath("//input[@id='password']");

    public By loginButtonLocator = By.xpath("//button[@id='login']");

    public By userNameErrorLocator = By.xpath("//span[@id='usernameErrorBlock']");
    public By passwordErrorLocator = By.xpath("//span[@id='passwordErrorBlock']");

    public By signupButtonLocator = By.xpath("//a[@id='registerLink']");

    public By nameBlockErrorLocator = By.xpath("//span[@id='nameErrorBlock']");
    public By mobileBlockErrorLocator = By.xpath("//span[@id='mobileErrorBlock']");
    public By passwordBlockErrorLocator = By.xpath("//span[@id='passwordErrorBlock']");

    public By sendOtpButtonLocator = By.xpath("//button[@id='EmailRegister']");


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

    public void navigateToLoginPage(){
        WebElement clickLoginSignupBtn = wait.clickable(loginSignupButtonLocator);
        clickLoginSignupBtn.click();
    }

    public void sendDataToInputBox(String phNumber , String password){
        WebElement sendMobileNumber = wait.visible(mobileNoEmailInputLocator);
        sendMobileNumber.sendKeys(phNumber);
        WebElement sendPassword = wait.visible(passwordInputLocator);
        sendPassword.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement clickLoginBtn = driver.findElement(loginButtonLocator);
        clickLoginBtn.click();
    }

    public String validatePhoneEmailErrorMessage(){
        WebElement errorMessage = wait.visible(userNameErrorLocator);
        return errorMessage.getText();
    }

    public void sendDataToInputBox2(String phNumber , String password){
        WebElement sendMobileNumber = wait.visible(mobileNoEmailInputLocator);
        sendMobileNumber.clear();
        sendMobileNumber.sendKeys(phNumber);
        WebElement sendPassword = wait.visible(passwordInputLocator);
        sendPassword.sendKeys(password);
    }

    public String validatePasswordErrorMessage(){
        WebElement passwordErrorMessage = wait.visible(passwordErrorLocator);
        return  passwordErrorMessage.getText();
    }

    public void navigateToSignupPage(){
        WebElement clickSignupBtn = wait.clickable(signupButtonLocator);
        clickSignupBtn.click();
    }

    public void clickSendOtpButton(){
        WebElement clickSendOtpBtn = wait.clickable(sendOtpButtonLocator);
        clickSendOtpBtn.click();
    }

    public String validateNameErrorMessage(){
        WebElement nameError = wait.visible(nameBlockErrorLocator);
        return nameError.getText();
    }

    public String validatePhoneErrorMessage() {
        WebElement phoneError = driver.findElement(mobileBlockErrorLocator);
        return phoneError.getText();
    }

    public String validatePasswordMessage(){
        WebElement passwordError = driver.findElement(passwordBlockErrorLocator);
        return passwordError.getText();
    }
}
