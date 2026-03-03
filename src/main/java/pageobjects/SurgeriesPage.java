package pageobjects;

import org.openqa.selenium.*;
import utilities.ExcelUtils;
import utilities.CommonCode;
import utilities.ScreenshotUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurgeriesPage {
    private final WebDriver driver;
    private final CommonCode wait;
    private final ScreenshotUtil Ss;

    private final By desktopSurgeriesLocator = By.xpath("//a[@title=\"surgery\" and @event=\"Nav Drawer:Interacted:Surgery\"]");
    private final By formCityDropdownLocator = By.xpath("//div[@data-qa-id=\"city-selector-container\"] ");
    private final By formCityOptionsLocator = By.xpath("(//div[@data-qa-id=\"city-name\"]/h1)[4]");
    private final By formAilmentDropdownLocator = By.xpath("//div[@data-qa-id=\"ailment-selector-container\"]");
    private final By formAilmentOptionsLocator = By.xpath("(//h1[contains(text(),\"ACL Repair\")])[2]");
    private final By formAilmentDropdownErrorMsgLocator = By.xpath("//div[@data-qa-id=\"ailment-selector-container\"]/following::div[1]");
    private final By formNameLocator = By.xpath("//input[@id=\"Name-Gen-Lead-Form\"]");
    private final By formNameErrorMsgLocator = By.xpath("//input[@id=\"Name-Gen-Lead-Form\"]/parent::div/following::div[1]");
    private final By formContactNumberLocator = By.xpath("//input[@id='Phone-Gen-Lead-Form']");
    private final By formContactNumberErrorMsgLocator = By.xpath("//input[@id='Phone-Gen-Lead-Form']/parent::div/following::div[1]");
    private final By formBookAppointmentButtonLocator = By.cssSelector("[data-qa-id='book-appointment-cta']");
    private final By formOtpSuccessMsgLocator = By.xpath("//p[@id=\"otpSentMsg\"]");
    private final By PopularSurgeriesListLocator = By.xpath("//h1[@data-qa-id=\"surgical-solution-sub-header\"]/../descendant::p");
    private final By OurDepartmentLocator = By.xpath("//div[@data-qa-id=\"our-department-wrapper\"]/descendant::h1");

    public SurgeriesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new CommonCode(driver, 20);
        this.Ss= new ScreenshotUtil();
    }
    public void navigateToSurgeriesPage() {
        WebElement link = driver.findElement(desktopSurgeriesLocator);
        try {
            link.click();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }    //Form Methods
    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
    public boolean isFormCityDropdownVisible() {
        return isElementDisplayed(formCityDropdownLocator);
    }
    public boolean isAilmentDropdownVisible() {
        return isElementDisplayed(formAilmentDropdownLocator);
    }
    public boolean isNameFieldVisible() {
        return isElementDisplayed(formNameLocator);
    }
    public boolean isContactNumberVisible() {
        return isElementDisplayed(formContactNumberLocator);
    }
    public boolean isBookAppointmentButtonVisible() {
        WebElement bookbtn = driver.findElement(formBookAppointmentButtonLocator);
        System.out.println(bookbtn.isSelected());
        System.out.println(bookbtn.isDisplayed());
        System.out.println(bookbtn.isEnabled());
        return isElementDisplayed(formBookAppointmentButtonLocator);
    }
    public void clickBookButton() {
        driver.findElement(formBookAppointmentButtonLocator).click();
    }
    public String getNameError() {
        return driver.findElement(formNameErrorMsgLocator).getText();
    }
    public String getContactError() {
        return driver.findElement(formContactNumberErrorMsgLocator).getText();
    }
    public String getAilmentError() {
        return driver.findElement(formAilmentDropdownErrorMsgLocator).getText();
    }
    public void fillTheForm(String Name, String ContactNumber) {
        wait.scrollIntoView(driver.findElement(formBookAppointmentButtonLocator));
        driver.findElement(formCityDropdownLocator).click();
        driver.findElement(formCityOptionsLocator).click();
        wait.clickable(formAilmentDropdownLocator).click();
        wait.clickable(formAilmentOptionsLocator).click();
        wait.visible(formNameLocator).sendKeys(Name);
        driver.findElement(formContactNumberLocator).sendKeys(ContactNumber);
    }
    public String submitForm() {

        driver.findElement(formBookAppointmentButtonLocator).click();
        driver.switchTo().frame(wait.visible(By.xpath("//iframe")));
        WebElement msg = driver.findElement(formOtpSuccessMsgLocator);
        Ss.takeScreenshot(driver,"SubmitFormOtpSuccessMsg");
        return msg.getText();
    }
    public List<WebElement> surgeriesList() {
        wait.scrollIntoView(driver.findElement(PopularSurgeriesListLocator));
        List<WebElement> surgerisList = driver.findElements(PopularSurgeriesListLocator);
        Ss.takeScreenshot(driver,"SurgeriesList");
        return surgerisList;
    }
    public List<String> extractSurgeriesList() {
        List<WebElement> list = driver.findElements(PopularSurgeriesListLocator);
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getText().trim();
            if (!name.isEmpty()) {
                rows.add(name);
            }
        }
        return rows;
    }
    public void saveSurgeriesListToExcelFromList()  {
        List<String> rows = extractSurgeriesList();
        ExcelUtils.writeList("Surgeries", "Popular Surgeries offered", rows);
    }
    public List<WebElement> ourDepartmentsList() {
        wait.scrollIntoView(driver.findElement(OurDepartmentLocator));
        Ss.takeScreenshot(driver,"DepartmentList");
        return driver.findElements(OurDepartmentLocator);
    }
    public List<List<String>> extractDepartmentsList() {
        List<WebElement> list = driver.findElements(OurDepartmentLocator);
        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i += 2) {
            String name = list.get(i).getText().trim();
            String count = (i + 1 < list.size()) ? list.get(i + 1).getText().trim() : "";
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