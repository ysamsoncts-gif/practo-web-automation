package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtils;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;

    By surgeriesTab = By.xpath("//a[@title='surgery']");
    By navCorporateTab = By.xpath("//span[@event='Nav Provider Marketing:Interacted:Plus Corporate']");
    By healthWellnessTab = By.xpath("//a[@event='Nav Provider Marketing:Interacted:Plus Corporate']");
    By bookDiagnosticTab = By.xpath("//span[text()='Book Diagnostic Tests']");
    public void NavigateToSurgeriesPage(){

    }

    public void NavigateToCorporateTab(){
        WebElement corporateClick = driver.findElement(navCorporateTab);
        corporateClick.click();
    }

    public void NavigateToHealthWellnessPage(){
        WebElement healthWellnessClick = driver.findElement(healthWellnessTab);
        healthWellnessClick.click();
    }

    public void NavigateToDiagnosticPage(){
        WebElement diagnosticTestClick = driver.findElement(bookDiagnosticTab);
        diagnosticTestClick.click();
    }

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);

    }

}
