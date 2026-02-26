package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.ExcelUtils;
import utilities.ScreenshotUtil;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class CorporatePage {
    private final WebDriver driver;
    private final WaitUtils wait;
    private final ScreenshotUtil ss;

    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "organizationName")
    private WebElement orgName;
    @FindBy(id = "contactNumber")
    private WebElement contactNumber;
    @FindBy(id = "officialEmailId")
    private WebElement emailId;
    @FindBy(id = "organizationSize")
    private WebElement orgSize;
    @FindBy(id = "interestedIn")
    private WebElement interestedIn;
    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement submit;
    @FindBy(xpath = "//span[@event='Nav Provider Marketing:Interacted:Plus Corporate']")
    private WebElement navCorporateTab;
    @FindBy(xpath = "//a[@event='Nav Provider Marketing:Interacted:Plus Corporate']")
    private WebElement healthWellnessTab;
    @FindBy(xpath = "//li[text() = 'Our Services']")
    private WebElement ourService;
    @FindBy(xpath = "//section[@id =\"service-umbrella\" ]//div[@class =\"u-text--bold text-gamma\"]")private List<WebElement> serviceItems;
    @FindBy(xpath = "//span[text() = \"Security & help\"]") private WebElement security;
    @FindBy(xpath = "//a[@event = \"Nav Bar:Interacted:Practo help\"]")private WebElement help;
    @FindBy(xpath = "//a[@href = \"mailto:support@practo.com\"]") private WebElement supportMail;
    @FindBy(xpath = "//a[@href='mailto:practo-nodal-officer-team@practo.com']") private WebElement nodalOfficerMail;
    @FindBy(xpath = "//a[@href=\"mailto:practo-grievance-officer-team@practo.com\"]")private WebElement grievanceOfficerMail;
    public CorporatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
        PageFactory.initElements(driver, this);
        this.ss = new ScreenshotUtil();
    }

    public void navigateToCorporateTab() {
        navCorporateTab.click();
    }

    public void navigateToHealthWellnessPage() {
        healthWellnessTab.click();
        ss.takeScreenshot(driver,"CorporatePage");
    }

    public void navigateToOurServices() {
        ourService.click();
    }
    public int getSizeserviceItems()
    {
        return this.serviceItems.size();
    }

    public void validatingData(String namee, String organizationNamee, String contactNumberr, String emailIdd, int n, int m) {
        name.sendKeys(namee);
        orgName.sendKeys(organizationNamee);
        contactNumber.sendKeys(contactNumberr);
        emailId.sendKeys(emailIdd);
        Select select1 = new Select(orgSize);
        select1.selectByIndex(n);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");
        Select select2 = new Select(interestedIn);
        select2.selectByIndex(m);
        ss.takeScreenshot(driver,"ValidatingData");
    }

    public List<String> listOfAllServices(){
        List<String> services = new ArrayList<>();
        for(int i = 0;i<serviceItems.size();i++)
        {
            String service = serviceItems.get(i).getText().trim();
            services.add(service);
        }
    return services;
}

public void saveServicesToExcel()
{
    List<String> services = listOfAllServices();
    ExcelUtils.writeList("services","Provided services",services);
    ss.takeScreenshot(driver,"ServicesList");
}


public void navigateToContactPage()
{
    security.click();
    help.click();
}
public String getSupportMail()
{
    wait.scrollIntoView(supportMail);
    return supportMail.getText();
}
public String getNodalOfficerMail()
{
    wait.scrollIntoView(nodalOfficerMail);
    return nodalOfficerMail.getText();
}

    public String getgrievanceOfficerMail()
    {
        wait.scrollIntoView(grievanceOfficerMail);
        return grievanceOfficerMail.getText();
    }
    public boolean isSubmitBtnEnable()
    {
        return submit.isEnabled();
    }

}


