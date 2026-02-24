package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

public class MedicinePage {
    private final WebDriver driver;
    private final WaitUtils wait;

    @FindBy(xpath = "//a[@title = 'tests']") private WebElement labTest;
  //  @FindBy(xpath = "//div[text() = \"Medicines\"]") private WebElement medicine;
  private final By medicineBy = By.xpath("//div[text() = \"Medicines\"]");
    public MedicinePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLabTest()
    {
        labTest.click();
    }

    public void navigateToMedicine()
    {
        wait.clickable(medicineBy).click();
    }
}
