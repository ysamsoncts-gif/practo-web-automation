package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExcelUtils;
import utilities.ScreenshotUtil;
import utilities.CommonCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class    DiagnosticPage {
    private WebDriver driver;
    private final CommonCode wait;
    private final ScreenshotUtil ss;


   // public By bookDiagnosticTab = By.xpath("//span[text()='Book Diagnostic Tests']");
    @FindBy (xpath = "//span[text()='Book Diagnostic Tests']")
    private WebElement bookDiagnosticTab;

    public By topCities = By.xpath(" //div[@class=\"u-margint--standard o-f-color--primary\"]");

//    public By citySearchBox = By.xpath("//input[@placeholder=\"Search for city\"]");
    @FindBy (xpath = "//input[@placeholder=\"Search for city\"]")
    private WebElement citySearchBox;

//    public By cityOption = By.xpath("(//div[text()='Pune'])[1]");
    @FindBy (xpath = "(//div[text()='Pune'])[1]")
    private WebElement cityOption;

    public By packageName = By.xpath("//h3[@data-aid=\"popular-health-packages-card-title\"]");

//    public By scrollBtn = By.xpath("(//div[@class='slick-arrow-wrapper'])[2]");
    @FindBy(xpath = "(//div[@class='slick-arrow-wrapper'])[2]" )
    private WebElement scrollBtn;

    public By packageAgeGroup  = By.xpath("//p[@class='c-package__age-text']");
    public By packagePrice     = By.xpath("//span[@class='c-package__price-current']");
    public By ageGroup = By.xpath("//p[text()='For Age:18-80yrs']");

    @FindBy (xpath = "(//i[@class=\"icon-ic_next_cheveron u-center\"])[2]")
    private WebElement arrowBtn;

    public By addToCart = By.xpath("//div[@class=\"o-f-color--plight\"]");

    public By testContainerLocator = By.xpath("//div[@class=\"u-pointer u-shadow--hover u-margin--less c-top-test-card\"]");

//    public By cartBtn = By.xpath("//div[@class=\"c-global-cart u-pointer\"]");
    @FindBy (xpath = "//div[@class=\"c-global-cart u-pointer\"]" )
    private WebElement cartBtn;
//    public By proceedCheckout = By.xpath("//div[contains(text(),'Proceed')]");
    @FindBy (xpath = "//div[contains(text(),'Proceed')]" )
    private WebElement proceedCheckout;


    //form xpaths
//    public By Name_Field = By.xpath("//input[@data-aid=\"patient-name\"]");
    @FindBy (xpath ="//input[@data-aid=\"patient-name\"]")
    private WebElement nameField;

//    public By Phone_Number = By.xpath("//input[@type=\"tel\"]");
    @FindBy (xpath = "//input[@type=\"tel\"]" )
    private WebElement phNumber;

//    public By Email = By.xpath("//input[@type=\"email\"]");
    @FindBy (xpath = "//input[@type=\"email\"]" )
    private WebElement emailId;

//    public By Gender_Male = By.xpath("//div[@data-aid='patient-gender-selected-MALE']");
    @FindBy (xpath = "//div[@data-aid='patient-gender-selected-MALE']")
    private WebElement genderMale;

  //  public By Dob_Day = By.xpath("//input[@data-aid=\"patient-age\"]");
    @FindBy (xpath = "//input[@data-aid=\"patient-age\"]")
    private WebElement dobAge;

//    public By Dob_Year_Dropdown = By.xpath("//div[contains(@class,'select')]//div[contains(text(),'Years') or contains(.,'Year')]");
    @FindBy (xpath = "//select [@class='c-order-v2__age__select']")
    private WebElement dobYearDropdown;

  //  public By ContinueBtn = By.xpath("//input[@data-aid=\"order-continue-button\"]");
    @FindBy (xpath ="//input[@data-aid=\"order-continue-button\"]")
    private WebElement continueBtn;

    //Error xpath
    @FindBy (xpath = "//div[text()='Enter valid name']")
    private WebElement errorName;

    @FindBy (xpath = "//div[text()='Required']")
    private WebElement errorDob;

    @FindBy (xpath = "//div[text()='Invalid Phone Number']")
    private WebElement errorPhnumber;

    @FindBy (xpath = "(//div[text()='Required'])[2]")
    private WebElement errorEmail;

//Methods
    public DiagnosticPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new CommonCode(driver, 20);
        PageFactory.initElements(driver, this);
        this.ss = new ScreenshotUtil();
    }

    public void navigateToDiagnosticPage() {

        bookDiagnosticTab.click();
    }
    public String GetPageTitle() {

        return driver.getTitle();
    }
    public void waitForTopCities() {

        wait.visible(topCities);
    }

    //get all city names and return as a list
    public List<String> getTopCities() {
        List<WebElement> cityElements = driver.findElements(topCities);
        List<String> cities = new ArrayList<>();

        for (WebElement city : cityElements) {
            cities.add(city.getText());
        }
        return cities;
    }

    //print to console
    public void printTopCities(List<String> cities) {
        System.out.println("=====TOP CITIES=====");
        for (String city : cities) {
            System.out.println(city);
        }
    }

    public List<String> extractTopcityList() {
        List<WebElement> list = driver.findElements(topCities);
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getText().trim();
            if (!name.isEmpty()) {
                rows.add(name);
            }
        }
        return rows;
    }

    public void saveCityListToExcelFromList()  {
        List<String> rows = extractTopcityList();
        ExcelUtils.writeList("Cities", "List of Top cities", rows);
    }

    public void searchBox(String city) {
        Actions action = new Actions(driver);
        WebElement search = wait.visible(By.xpath("//input[@placeholder=\"Search for city\"]"));
        search.clear();
        search.sendKeys(city);
        search.click();

    }

    public void chooseCity(String city) {
        wait.clickable(cityOption).click();
    }


    public void clickScroll(){

        Actions action = new Actions(driver);
        }

    public void printPackageDetails() {
    List<WebElement> names  = driver.findElements(packageName);
    List<WebElement> ages   = driver.findElements(packageAgeGroup);
    List<WebElement> prices = driver.findElements(packagePrice);
    System.out.println("======= POPULAR HEALTH PACKAGES =======");

    for (int i = 0; i < names.size(); i++) {

        String name  = names.get(i).getText().trim();
        if (name.isEmpty()) continue; // <<< prevents the blank lines

        String age   = (i < ages.size())   ? ages.get(i).getText().trim()   : "";
        String price = (i < prices.size()) ? prices.get(i).getText().trim() : "";

        System.out.println(name + " | " + (age.isEmpty() ? "N/A" : age) + " | " + (price.isEmpty() ? "N/A" : price));
        arrowBtn.click();
    }
}



    public BigDecimal addTopBookedTestsAndGetTotal() {
        // price inside a test card: //span[@class="u-font-bold o-font-size--18"]
        BigDecimal runningTotal = BigDecimal.ZERO;
        int added = 0;
        final int MAX_TO_ADD = 3; // adjust if needed

        // Wait until at least one container is present
        wait.visible(By.xpath("//div[@class=\"u-pointer u-shadow--hover u-margin--less c-top-test-card\"]"));

        List<WebElement> containers = driver.findElements(testContainerLocator);
        if (containers.isEmpty()) {
            System.out.println("[addTopBookedTestsAndGetTotal] No test containers found.");
        }

        for (WebElement container : containers) {
            if (added >= MAX_TO_ADD) break;

            try {
                // 1) Scroll the CURRENT container into view (not the locator)
                wait.scrollIntoView(container);

                // 2) Price lookup MUST be relative to the container
                WebElement priceEl = container.findElement(
                        By.xpath(".//span[@class='u-font-bold o-font-size--18']"));
                String rawPriceText = priceEl.getText();
                BigDecimal price = parsePrice(rawPriceText);

                // 3) Add-to-cart MUST also be scoped to the container
                //    If your addToCart locator is global ("//..."), switch to a relative one (".//...").
                //    Otherwise, this can still accidentally hit the wrong element.
                WebElement addBtn;
                try {
                    // Try relative first (recommended). Update the XPath to your actual relative locator.
                    addBtn = container.findElement(By.xpath(".//div[@class='o-f-color--plight']"));
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    // Fallback to your existing By (less reliable, but keeps your structure).
                    addBtn = container.findElement(addToCart);
                }

                // 4) Click the add button safely
                wait.safeClick(addBtn);
                runningTotal = runningTotal.add(price);
                added++;

                wait.clickable(cartBtn).click();
                wait.clickable(By.xpath("(//i[@class='icon-ic_next_cheveron u-center'])[1]")).click();

            } catch (org.openqa.selenium.StaleElementReferenceException sere) {
                containers = driver.findElements(testContainerLocator);
            } catch (org.openqa.selenium.NoSuchElementException nse) {
                System.out.println("[addTopBookedTestsAndGetTotal] Missing price/add button in a container, skipping.");
            } catch (org.openqa.selenium.ElementClickInterceptedException eci) {
                System.out.println("[addTopBookedTestsAndGetTotal] Click intercepted; attempting JS click fallback.");
                try {
                    WebElement retryAdd = container.findElement(By.xpath(".//div[@class='o-f-color--plight']"));
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", retryAdd);
                } catch (Exception ex) {
                    System.out.println("[addTopBookedTestsAndGetTotal] JS click fallback failed; skipping this container.");
                }
            }
        }

        System.out.println("Total of added tests: " + runningTotal + " | Items added: " + added);
        return runningTotal;
    }
    public void proceedToCheckout() {
        wait.scrollIntoView(cartBtn);
        wait.clickable(cartBtn).click();
        proceedCheckout.click();
    }


    private BigDecimal parsePrice(String text) {
        if (text == null) return BigDecimal.ZERO;

        // Keep digits and decimal point only. This is robust for most locales when site renders ASCII decimal point.
        String numeric = text.replaceAll("[^0-9.]", "");

        if (numeric.isEmpty()) return BigDecimal.ZERO;

        // If there are multiple dots due to malformed text, keep first two segments (simple guard).
        int firstDot = numeric.indexOf('.');
        int lastDot = numeric.lastIndexOf('.');
        if (firstDot != -1 && lastDot != firstDot) {
            // remove other dots
            StringBuilder sb = new StringBuilder();
            boolean dotKept = false;
            for (char c : numeric.toCharArray()) {
                if (c == '.' && !dotKept) {
                    sb.append(c);
                    dotKept = true;
                } else if (c != '.') {
                    sb.append(c);
                }
            }
            numeric = sb.toString();
        }

        try {
            return new BigDecimal(numeric);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
    public double getTotalPriceFromCart() {
        wait.scrollIntoView(wait.visible(By.xpath("//div[@class =\"u-font-bold o-font-size--16\"]/span")));
        return Double.parseDouble(
                driver.findElement(By.xpath("//div[@class =\"u-font-bold o-font-size--16\"]/span"))
                        .getText()
                        .replace("₹", "")
                        .trim()
        );
    }

    public void sendName(String name){
        wait.visible(By.xpath("//input[@data-aid=\"patient-name\"]")).sendKeys(name);
    }
    public void phoneNumber(String num){
        phNumber.sendKeys(num);
    }
    public void sendEmail (String email){
        emailId.sendKeys(email);
    }
    public void genderOption(){
        genderMale.click();
    }
    public void sendDobAge(String age){
        dobAge.sendKeys(age);
    }
    public void selectYearDropDown(){
        dobYearDropdown.click();
    }
    public void clickContinueBtn(){
        continueBtn.click();
        ss.takeScreenshot(driver,"DiagnosticPageForm");
    }
    public String sendErrorName(){
        return errorName.getText();
    }
    public String sendErrorDob(){
        return errorDob.getText();
    }
    public String sendErrorMobile(){
        return errorPhnumber.getText();
    }
    public String sendErrorEmail(){
        return errorEmail.getText();
    }


}
