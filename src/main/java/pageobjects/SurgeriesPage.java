package pageobjects;

import org.openqa.selenium.*;
import utilities.WaitUtils;

public class SurgeriesPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By desktopSurgeriesLocator = By.xpath("//a[@title=\"surgery\" and @event=\"Nav Drawer:Interacted:Surgery\"]");
    private final By formCityDropdownLocator = By.xpath("//div[@data-qa-id=\"city-selector-container\"] ");
    private final By formAilmentDropdownLocator =By.xpath("//div[@data-qa-id=\"ailment-selector-container\"]");
    private final By formAilmentDropdownErrorMsgLocator =By.xpath("//div[@data-qa-id=\"ailment-selector-container\"]/following::div[1]");
    private final By formNameLocator =By.xpath("//input[@id=\"Name-Gen-Lead-Form\"]");
    private final By formNameErrorMsgLocator =By.xpath("//input[@id=\"Name-Gen-Lead-Form\"]/parent::div/following::div[1]");
    private final By formContactNumberLocator = By.xpath("//input[@id='Phone-Gen-Lead-Form']");
    private final By formContactNumberErrorMsgLocator =By.xpath("//input[@id='Phone-Gen-Lead-Form']/parent::div/following::div[1]");
    private final By formBookAppointmentButtonLocator = By.cssSelector("[data-qa-id='book-appointment-cta']");


    public SurgeriesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }
    public void navigateToSurgeriesPage() {
        WebElement link = driver.findElement(desktopSurgeriesLocator);
        try {
            link.click();
        } catch (WebDriverException e) {
            System.out.println("Falling to js ");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }
    public boolean isElementDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();
    }
    public boolean isFormCityDropdownVisible(){
        return isElementDisplayed(formCityDropdownLocator);
    }
    public boolean isAilmentDropdownVisible(){
        return isElementDisplayed(formAilmentDropdownLocator);
    }
    public boolean isNameFieldVisible(){
        return isElementDisplayed(formNameLocator);
    }
    public boolean isContactNumberVisible(){
        return isElementDisplayed(formContactNumberLocator);
    }
    public boolean isBookAppointmentButtonVisible(){
        return isElementDisplayed(formBookAppointmentButtonLocator);
    }
    public void clickBookButton(){
        driver.findElement(formBookAppointmentButtonLocator).click();
    }
    public String getNameError(){
        return driver.findElement(formNameErrorMsgLocator).getText();
    }
    public String getContactError(){
        return driver.findElement(formContactNumberErrorMsgLocator).getText();
    }
    public String getAilmentError(){
        return driver.findElement(formAilmentDropdownErrorMsgLocator).getText();
    }
}