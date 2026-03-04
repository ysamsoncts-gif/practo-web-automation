package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExcelUtils;
import utilities.CommonCode;
import utilities.ScreenshotUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurgeriesPage {
    private final WebDriver driver;
    private final CommonCode cc;
    private final ScreenshotUtil Ss;

    @FindBy(xpath = "//a[@title='surgery' and @event='Nav Drawer:Interacted:Surgery']")
    private WebElement desktopSurgeriesNavbarLink;
    @FindBy(xpath = "//div[@data-qa-id='city-selector-container']")
    private WebElement formCityDropdownArrow;
    @FindBy(xpath = "(//div[@data-qa-id='city-name']/h1)[4]")
    private WebElement formCityOptionRadiobutton;
    @FindBy(xpath = "//div[@data-qa-id='ailment-selector-container']")
    private WebElement formAilmentDropdownArrow;
    @FindBy(xpath = "(//h1[contains(text(),'ACL Repair')])[2]")
    private WebElement formAilmentOptionRadioButton;
    @FindBy(xpath = "//div[@data-qa-id='ailment-selector-container']/following::div[1]")
    private WebElement formAilmentBoxErrorMsg;
    @FindBy(xpath = "//input[@id='Name-Gen-Lead-Form']")
    private WebElement formNameInputBox;
    @FindBy(xpath = "//input[@id='Name-Gen-Lead-Form']/parent::div/following::div[1]")
    private WebElement formNameErrorMsg;
    @FindBy(xpath = "//input[@id='Phone-Gen-Lead-Form']")
    private WebElement formContactNumberInputBox;
    @FindBy(xpath = "//input[@id='Phone-Gen-Lead-Form']/parent::div/following::div[1]")
    private WebElement formContactNumberErrorMsg;
    @FindBy(css = "[data-qa-id='book-appointment-cta']")
    private WebElement formBookAppointmentButton;
    private final By otpIframe = By.xpath("//iframe");
    @FindBy(xpath = "//p[@id='otpSentMsg']")
    private WebElement formOtpSuccessMsg;
    @FindBy(xpath = "//h1[@data-qa-id='surgical-solution-sub-header']/../descendant::p")
    private List<WebElement> popularSurgeriesItems;
    @FindBy(xpath = "//div[@data-qa-id='our-department-wrapper']/descendant::h1")
    private List<WebElement> ourDepartmentItems;
    @FindBy(xpath = "(//h1)[1]")
    private WebElement pageHeaderText;
    @FindBy(xpath = "//img[@alt='PCS Logo']")
    private WebElement pageLogo;

    public SurgeriesPage(WebDriver driver) {
        this.driver = driver;
        this.cc = new CommonCode(driver, 20);
        this.Ss = new ScreenshotUtil();
        PageFactory.initElements(driver, this);
    }
    public void navigateToSurgeriesPage() {
        try {
            cc.clickable(desktopSurgeriesNavbarLink).click();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", desktopSurgeriesNavbarLink);
        }
    }
    public boolean isformCityDropdownArrowVisible() {
        return cc.isElementDisplayed(formCityDropdownArrow);
    }
    public boolean isAilmentDropdownVisible() {
        return cc.isElementDisplayed(formAilmentDropdownArrow);
    }
    public boolean isNameFieldVisible() {
        return cc.isElementDisplayed(formNameInputBox);
    }
    public boolean isContactNumberVisible() {
        return cc.isElementDisplayed(formContactNumberInputBox);
    }
    public boolean isBookAppointmentButtonVisible() {
        return cc.isElementDisplayed(formBookAppointmentButton);
    }
    public void clickBookButton() {
        cc.clickable(formBookAppointmentButton).click();
    }
    public String getNameError() {
        return cc.getText(formNameErrorMsg);
    }
    public String getContactError() {
        return cc.getText(formContactNumberErrorMsg);
    }
    public String getAilmentError() {
        return cc.getText(formAilmentBoxErrorMsg);
    }
    public String getHeadingText(){
        return cc.getText(pageHeaderText);
    }
    public boolean isLogoDisplayed(){
        System.out.println("logo displayed "+pageLogo.isDisplayed());
//        System.out.println("logo enabled "+pageLogo.isEnabled());
        return cc.isElementDisplayed(pageLogo);
    }
    public String getLogoAttribute(String attribute){
        return pageLogo.getAttribute("alt");
    };
    public void fillTheForm(String name, String contactNumber) {
        cc.scrollIntoView(formBookAppointmentButton);
        cc.clickable(formCityDropdownArrow).click();
        cc.clickable(formCityOptionRadiobutton).click();
        cc.clickable(formAilmentDropdownArrow).click();
        cc.clickable(formAilmentOptionRadioButton).click();
        cc.visible(formNameInputBox).clear();
        formNameInputBox.sendKeys(name);
        cc.visible(formContactNumberInputBox).clear();
        formContactNumberInputBox.sendKeys(contactNumber);
    }
    public String submitForm() {
        cc.clickable(formBookAppointmentButton).click();
        WebElement iframe = cc.visible(otpIframe);
        driver.switchTo().frame(iframe);
        WebElement msg = cc.visible(formOtpSuccessMsg);
        Ss.takeScreenshot(driver, "SubmitFormOtpSuccessMsg");
        String text = msg.getText();
        driver.switchTo().defaultContent();
        return text;
    }
    public List<WebElement> surgeriesList() {
        if (!popularSurgeriesItems.isEmpty()) {
            cc.scrollIntoView(popularSurgeriesItems.get(0));
        }
        Ss.takeScreenshot(driver, "SurgeriesList");
        return popularSurgeriesItems;
    }
    public List<String> extractSurgeriesList() {
        List<String> rows = new ArrayList<>();
        for (WebElement el : popularSurgeriesItems) {
            String name = el.getText().trim();
            if (!name.isEmpty()) {
                rows.add(name);
            }
        }
        return rows;
    }
    public void saveSurgeriesListToExcelFromList() {
        List<String> rows = extractSurgeriesList();
        ExcelUtils.writeList("Surgeries", "Popular Surgeries offered", rows);
    }
    public List<WebElement> ourDepartmentsList() {
        if (!ourDepartmentItems.isEmpty()) {
            cc.scrollIntoView(ourDepartmentItems.get(0));
        }
        Ss.takeScreenshot(driver, "DepartmentList");
        return ourDepartmentItems;
    }
    public List<List<String>> extractDepartmentsList() {
        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < ourDepartmentItems.size(); i += 2) {
            String name = ourDepartmentItems.get(i).getText().trim();
            String count = (i + 1 < ourDepartmentItems.size()) ? ourDepartmentItems.get(i + 1).getText().trim() : "";
            if (!name.isEmpty()) {
                rows.add(Arrays.asList(name, count));
            }
        }
        return rows;
    }
    public void saveDepartmentsToExcelFromFlatList() {
        List<List<String>> rows = extractDepartmentsList();
        List<String> headers = Arrays.asList("Department Name", "Ailment Count");
        ExcelUtils.writeTable("Our Departments", headers, rows);
    }
}