package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExcelUtils;
import utilities.ScreenshotUtil;
import utilities.CommonCode;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final CommonCode cc;
    private final ScreenshotUtil ss;

    @FindBy (xpath = "(//i[@class='practo_logo_new'])[3]" )
    private WebElement practoLogoLocator;

    private By cardLinkLocator = By.xpath("//a[@class='card-link']");
    private By tabLinkLocator = By.xpath("//div[@class='product-tab__title']");

    @FindBy (xpath = "//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
    private WebElement searchBarLocator;

    @FindBy (xpath = "(//div[contains(text(),\"Hospital\")])[4]")
    private WebElement hospitalOptionLocator;
    @FindBy (xpath = "(//div[starts-with(text(),'Doctor')])[1]")
    private  WebElement doctorOptionLocator;

    @FindBy (xpath = "//input[@placeholder='Search location']")
    private WebElement locationSearchBarLocator;

    @FindBy (xpath = "(//div[contains(text(),\"Bangalore\")])")
    private WebElement bangaloreOptionLocator;
    @FindBy (xpath = "(//div[@data-qa-id='omni-suggestion-main'])[1]")
    private  WebElement jpNagarOptionLocator;

    @FindBy (xpath ="//span[contains(text(),\"JP Nagar\" )]" )
    private WebElement resultHospitalLocationLocator;
    public By resultHospitalNameLocator = By.xpath("//h2[@class='line-1']");
    @FindBy (xpath ="(//h2[@class='line-1'])[10]" )
    private WebElement resultHospitalLastNameLocator;

    @FindBy (xpath = "//div[@data-qa-id='doctor_gender_section']")
    private WebElement genderTabLocator;
    @FindBy (xpath = "//span[text()='Male Doctor']")
    private WebElement maleDoctorTabLocator;

    @FindBy (xpath ="//div[@data-qa-id='doctor_review_count_section']")
    private WebElement doctorReviewTabLocator;
    @FindBy (xpath ="(//li[@role='option'])[4]")
    private WebElement patientFeedbackTabLocator;
    @FindBy (xpath = "//div[@data-qa-id='years_of_experience_section']")
    private WebElement experienceTabLocator;
    @FindBy (xpath = "//span[text()='10+ Years of experience']")
    private WebElement yearsOfExperienceLocator;

    @FindBy (xpath = "//span[@data-qa-id='all_filters']" )
    private WebElement allFiltersTabLocator;
    @FindBy (xpath = "//div[@data-qa-id='Apollo_Clinic_checkbox']")
    private  WebElement apolloCheckBoxLocator;
    @FindBy (xpath = "//span[@data-qa-id='Above ₹500_label']")
    private WebElement feesRadioLocator;
    @FindBy (xpath = "//span[@data-qa-id='Available Tomorrow_label']" )
    private WebElement availabilityCheckBoxLocator;
    @FindBy (xpath = "//div[@data-qa-id='sort_by_section']" )
    private WebElement sortByTabLocator;

    @FindBy (xpath = "//li[@data-qa-id='experience_years']" )
    private WebElement experienceFilterLocator;

    public By resultDoctorNameLocator = By.xpath("//h2[@data-qa-id='doctor_name']");
    @FindBy (xpath = "//span[@data-qa-id='practice_city']")
    private WebElement resultDoctorLocationLocator;
    @FindBy (xpath = "(//h2[@data-qa-id='doctor_name'])[10]")
    private WebElement resultDoctorLastNameLocator;

    @FindBy (xpath = "//a[text()='Login / Signup']")
    private WebElement loginSignupButtonLocator;

    @FindBy (xpath = "//input[@id='username']")
    private WebElement mobileNoEmailInputLocator;
    @FindBy (xpath = "//input[@id='password']")
    private WebElement passwordInputLocator;

    @FindBy (xpath = "//button[@id='login']")
    private WebElement loginButtonLocator;

    @FindBy (xpath = "//span[@id='usernameErrorBlock']")
    private WebElement userNameErrorLocator;

    @FindBy (xpath = "//span[@id='passwordErrorBlock']")
    private WebElement passwordErrorLocator;

    @FindBy (xpath = "//a[@id='registerLink']")
    private WebElement signupButtonLocator;

    @FindBy (xpath = "//span[@id='nameErrorBlock']")
    private WebElement nameBlockErrorLocator;
    @FindBy (xpath = "//span[@id='mobileErrorBlock']")
    private WebElement mobileBlockErrorLocator;
    @FindBy (xpath = "//span[@id='passwordErrorBlock']")
    private WebElement passwordBlockErrorLocator;

    public By sendOtpButtonLocator = By.xpath("//button[@id='EmailRegister']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.cc = new CommonCode(driver, 20);
        this.ss = new ScreenshotUtil();
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isPageLogoDisplayed(){
        cc.visible(practoLogoLocator);
        return practoLogoLocator.isDisplayed();
    }

    public int numberOfCardLinkPresent(){
        List <WebElement> cardLinks = driver.findElements(cardLinkLocator);
        return cardLinks.size();
    }

    public int numberOfLinkTabPresent(){
        List<WebElement> tabLinks = driver.findElements(tabLinkLocator);
        return tabLinks.size();
    }

    public void selectSearchHospitalClinic(String hospital) {
        searchBarLocator.click();
        searchBarLocator.sendKeys(hospital);
        cc.clickable(hospitalOptionLocator).click();
    }

    public void selectSearchDoctor(String doctor) {
        searchBarLocator.click();
        searchBarLocator.sendKeys(doctor);
        cc.clickable(doctorOptionLocator).click();
    }

    public void selectSearchLocation(String location)  {
        locationSearchBarLocator.clear();
        locationSearchBarLocator.click();
        locationSearchBarLocator.sendKeys(location);
        cc.clickable(bangaloreOptionLocator).click();
    }

    public void selectSubSearchLocation(String location)  {
        locationSearchBarLocator.clear();
        locationSearchBarLocator.click();
        locationSearchBarLocator.sendKeys(location);
        cc.clickable(jpNagarOptionLocator).click();
        cc.present(By.xpath("//span[contains(text(),\"JP Nagar\" )]" ));
    }

    public boolean verifyHospitalName() {
        cc.present(By.xpath("//span[contains(text(),\"JP Nagar\" )]" ));
        return resultHospitalLocationLocator.getText().startsWith("JP Nagar");
    }

    public void storeHospitalName() {
        List<WebElement> hospitalNames = driver.findElements(resultHospitalNameLocator);
        cc.scrollIntoView(resultHospitalLastNameLocator);
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
        WebElement clickGender = cc.clickable(genderTabLocator);
        clickGender.click();
    }

    public void selectFilterDoctorMale(){
        maleDoctorTabLocator.click();
    }

    public void selectDoctorReview(){
         cc.clickable(doctorReviewTabLocator);
         doctorReviewTabLocator.click();
    }

    public void selectFilterPatientFeedback(){
        cc.clickable(patientFeedbackTabLocator).click();
    }

    public void selectExperience(){
         cc.clickable(experienceTabLocator).click();
    }

    public void selectFilter10Years(){
        cc.clickable(yearsOfExperienceLocator).click();
    }

    public void selectALlFilters(){
         cc.clickable(allFiltersTabLocator).click();
    }

    public void selectApolloCheckBox() {
        cc.clickable(apolloCheckBoxLocator).click();
    }

    public void selectFeesRadio() {
       cc.clickable(feesRadioLocator).click();
    }

    public void selectAvailabilityCheckBox() {
         cc.clickable(availabilityCheckBoxLocator).click();
    }

    public void selectSortByFilter(){
        cc.clickable(sortByTabLocator).click();
    }

    public void selectExperienceFilter(){
        cc.clickable(experienceFilterLocator).click();
    }

    public void storeDoctorName(){
        List <WebElement> doctorNames = driver.findElements(resultDoctorNameLocator);
        cc.scrollIntoView(resultDoctorLastNameLocator);
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
            cc.present(By.xpath("//span[@data-qa-id='practice_city']"));
            return resultDoctorLocationLocator.getText().startsWith("Bangalore");
        }

    public void navigateToLoginPage(){
        cc.clickable(loginSignupButtonLocator).click();
    }

    public void sendDataToInputBox(String phNumber , String password){
        cc.visible(mobileNoEmailInputLocator).sendKeys(phNumber);
        cc.visible(passwordInputLocator).sendKeys(password);
    }

    public void clickLoginButton(){
        loginButtonLocator.click();
        ss.takeScreenshot(driver,"Login_Form_Validation");
    }

    public String validatePhoneEmailErrorMessage(){
        return cc.visible(userNameErrorLocator).getText();
    }

    public void sendDataToInputBoxB(String phNumber , String password){
        WebElement sendMobileNumber = cc.visible(mobileNoEmailInputLocator);
        sendMobileNumber.clear();
        sendMobileNumber.sendKeys(phNumber);
        WebElement sendPassword = cc.visible(passwordInputLocator);
        sendPassword.sendKeys(password);
    }

    public String validatePasswordErrorMessage(){
       return cc.visible(passwordErrorLocator).getText();
    }

    public void navigateToSignupPage(){
        cc.clickable(signupButtonLocator).click();
    }

    public void clickSendOtpButton(){
        WebElement clickSendOtpBtn = cc.clickable(sendOtpButtonLocator);
        clickSendOtpBtn.click();
        ss.takeScreenshot(driver,"Signup_Form_Validation");
    }

    public String validateNameErrorMessage(){
        return cc.visible(nameBlockErrorLocator).getText();
    }

    public String validatePhoneErrorMessage() {
        return driver.findElement(By.xpath("//span[@id='mobileErrorBlock']")).getText();
    }

    public String validatePasswordMessage(){
        return passwordBlockErrorLocator.getText();
    }
}
