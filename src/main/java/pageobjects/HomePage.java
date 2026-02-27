package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ExcelUtils;
import utilities.ScreenshotUtil;
import utilities.WaitUtils;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final ScreenshotUtil ss;

    public By practoLogoLocator = By.xpath("(//i[@class='practo_logo_new'])[3]");

    public By searchBarLocator = By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']");

    public By hospitalOptionLocator = By.xpath("(//div[contains(text(),\"Hospital\")])[4]");
    public By doctorOptionLocator = By.xpath("(//div[starts-with(text(),'Doctor')])[1]");

    public By locationSearchBarLocator = By.xpath("//input[@placeholder='Search location']");

    public By BangaloreOptionLocator = By.xpath("(//div[contains(text(),\"Bangalore\")])");
    public By JPNagarOptionLocator = By.xpath("(//div[@data-qa-id='omni-suggestion-main'])[1]");

    public By resultHospitalLocationLocator = By.xpath("//span[contains(text(),\"JP Nagar\" )]");
    public By resultHospitalNameLocator = By.xpath("//h2[@class='line-1']");
    public By resultHospitalLastNameLocator = By.xpath("(//h2[@class='line-1'])[10]");

    public By genderTabLocator= By.xpath("//div[@data-qa-id='doctor_gender_section']");
    public By maleDoctorTabLocator = By.xpath("//span[text()='Male Doctor']");

    public By doctorReviewTabLocator = By.xpath("//div[@data-qa-id='doctor_review_count_section']");
    public By patientFeedbackTabLocator = By.xpath("(//li[@role='option'])[4]");

    public By experienceTabLocator = By.xpath("//div[@data-qa-id='years_of_experience_section']");
    public By yearsOfExperienceLocator = By.xpath("//span[text()='10+ Years of experience']");

    public By allFiltersTabLocator = By.xpath("//span[@data-qa-id='all_filters']");
    public By apolloCheckBoxLocator = By.xpath("//div[@data-qa-id='Apollo_Clinic_checkbox']");
    public By feesRadioLocator = By.xpath("//span[@data-qa-id='Above ₹500_label']");
    public By availabilityCheckBoxLocator = By.xpath("//span[@data-qa-id='Available Tomorrow_label']");

    public By sortByTabLocator = By.xpath("//div[@data-qa-id='sort_by_section']");
    public By experienceFilterLocator = By.xpath("//li[@data-qa-id='experience_years']");

    public By resultDoctorNameLocator = By.xpath("//h2[@data-qa-id='doctor_name']");
    public By resultDoctorLocationLocator = By.xpath("//span[@data-qa-id='practice_city']");
    public By resultDoctorLastNameLocator = By.xpath("(//h2[@data-qa-id='doctor_name'])[10]");

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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
        this.ss = new ScreenshotUtil();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isPageLogoDisplayed(){
        WebElement logo = wait.visible(practoLogoLocator);
        return logo.isDisplayed();
    }

    public void selectSearchHospitalClinic(String hospital) {
        WebElement sendHospital = driver.findElement(searchBarLocator);
        sendHospital.click();
        sendHospital.sendKeys(hospital);
        wait.clickable(hospitalOptionLocator).click();
    }

    public void selectSearchDoctor(String doctor) {
        WebElement sendHospital = driver.findElement(searchBarLocator);
        sendHospital.click();
        sendHospital.sendKeys(doctor);
        wait.clickable(doctorOptionLocator).click();
    }

    public void selectSearchLocation(String location)  {
        WebElement sendSearchLocation = driver.findElement(locationSearchBarLocator);
        sendSearchLocation.clear();
        sendSearchLocation.click();
        sendSearchLocation.sendKeys(location);
        wait.clickable(BangaloreOptionLocator).click();
    }

    public void selectSubSearchLocation(String location)  {
        WebElement sendSubLocation = driver.findElement(locationSearchBarLocator);
        sendSubLocation.clear();
        sendSubLocation.click();
        sendSubLocation.sendKeys(location);
        wait.clickable(JPNagarOptionLocator).click();
        wait.present(resultHospitalLocationLocator);
    }

    public boolean verifyHospitalName() {
        wait.present(resultHospitalLocationLocator);
        WebElement hospitalNameLocation = driver.findElement(resultHospitalLocationLocator);
        return hospitalNameLocation.getText().startsWith("JP Nagar");
    }

    public void storeHospitalName() {
        List<WebElement> hospitalNames = driver.findElements(resultHospitalNameLocator);
        wait.scrollIntoView(driver.findElement(resultHospitalLastNameLocator));
        for (WebElement names : hospitalNames) {
            System.out.println(names.getText());
        }
    }

    public List<String> extractHospitalList() {
        List<WebElement> list = driver.findElements(resultHospitalNameLocator);
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getText().trim();
            if (!name.isEmpty()) {
                rows.add(name);
            }
        }
        return rows;
    }

    public void saveHospitalListToExcelFromList()  {
        List<String> rows = extractHospitalList();
        ExcelUtils.writeList("HospitalNames", "List of hospitals in JP Nagar,Bangalore", rows);
    }

    public void selectGender(){
        WebElement clickGender = wait.clickable(genderTabLocator);
        clickGender.click();
    }

    public void selectFilterDoctorMale(){
        WebElement clickMaleDoctor = driver.findElement(maleDoctorTabLocator);
        clickMaleDoctor.click();
    }

    public void selectDoctorReview(){
        WebElement clickReviewTab = wait.clickable(doctorReviewTabLocator);
        clickReviewTab.click();
    }

    public void selectFilterPatientFeedback(){
        WebElement clickPatientStories = wait.clickable(patientFeedbackTabLocator);
        clickPatientStories.click();
    }

    public void selectExperience(){
        WebElement clickExperience = wait.clickable(experienceTabLocator);
        clickExperience.click();
    }

    public void selectFilter10Years(){
        WebElement click10Years = wait.clickable(yearsOfExperienceLocator);
        click10Years.click();
    }

    public void selectALlFilters(){
        WebElement clickAllFilters = wait.clickable(allFiltersTabLocator);
        clickAllFilters.click();
    }

    public void selectApolloCheckBox() {
        WebElement clickApolloCheckBox = wait.clickable(apolloCheckBoxLocator);
        clickApolloCheckBox.click();
    }

    public void selectFeesRadio() {
        WebElement clickFeesRadio =wait.clickable(feesRadioLocator);
        clickFeesRadio.click();
    }

    public void selectAvailabilityCheckBox() {
        WebElement clickAvailabilityCheckBox = wait.clickable(availabilityCheckBoxLocator);
        clickAvailabilityCheckBox.click();
    }

    public void selectSortByFilter(){
        WebElement clickSortBy = wait.clickable(sortByTabLocator);
        clickSortBy.click();
    }

    public void selectExperienceFilter(){
        WebElement clickExperience = wait.clickable(experienceFilterLocator);
        clickExperience.click();
    }

    public void storeDoctorName(){
        List <WebElement> doctorNames = driver.findElements(resultDoctorNameLocator);
        wait.scrollIntoView(driver.findElement(resultDoctorLastNameLocator));
        for(WebElement names:doctorNames){
            System.out.println(names.getText());
        }
    }

    public List<String> extractDoctorList() {
        List<WebElement> list = driver.findElements(resultDoctorNameLocator);
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getText().trim();
            if (!name.isEmpty()) {
                rows.add(name);
            }
        }
        return rows;
    }

    public void saveDoctorListToExcelFromList()  {
        List<String> rows = extractDoctorList();
        ExcelUtils.writeList("DoctorNames", "List of hospitals in JP Nagar,Bangalore", rows);
    }

    public boolean verifyDoctorLocation(){
            wait.present(resultDoctorLocationLocator);
            WebElement doctorNameLocation = driver.findElement(resultDoctorLocationLocator);
            return doctorNameLocation.getText().startsWith("Bangalore");
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
        ss.takeScreenshot(driver,"Login_Form_Validation");
    }

    public String validatePhoneEmailErrorMessage(){
        WebElement errorMessage = wait.visible(userNameErrorLocator);
        return errorMessage.getText();
    }

    public void sendDataToInputBoxB(String phNumber , String password){
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
        ss.takeScreenshot(driver,"Signup_Form_Validation");
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
