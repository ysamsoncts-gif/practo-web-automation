package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtils;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;

    public By searchBarLocator = By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']");

    public By hospitalOptionLocator = By.xpath("(//div[contains(text(),\"Hospital\")])[4]");
    public By doctorOptionLocator = By.xpath("(//div[starts-with(text(),'Doctor')])[1]");

    public By locationSearchBarLocator = By.xpath("//input[@placeholder='Search location']");

    public By BangaloreOptionLocator = By.xpath("(//div[contains(text(),\"Bangalore\")])");
    public By JPNagarOptionLocator = By.xpath("(//div[@data-qa-id='omni-suggestion-main'])[1]");

    public By resultHospitalLocationLocator = By.xpath("//span[contains(text(),\"JP Nagar\" )]");

    public By resultHospitalNameLocator = By.xpath("//h2[@class='line-1']");

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



    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public String getPageTitle() {
        return driver.getTitle();
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
        for (WebElement names : hospitalNames) {
            System.out.println(names.getText());
        }
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
        for(WebElement names:doctorNames){
            System.out.println(names.getText());
        }
    }

    public boolean verifyDoctorLocation(){
            wait.present(resultDoctorLocationLocator);
            WebElement doctorNameLocation = driver.findElement(resultDoctorLocationLocator);
            return doctorNameLocation.getText().startsWith("Bangalore");
        }
}
