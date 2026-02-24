package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;
import pageobjects.MedicinePage;

public class TC_17_NavigateToMedicinePage extends BaseTest {
    @Test
    public void navigateToMedicinePage(){
        MedicinePage mp = new MedicinePage(driver);
        mp.navigateToLabTest();
        mp.navigateToMedicine();

    }

}


