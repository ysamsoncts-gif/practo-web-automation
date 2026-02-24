package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_24_PopularSurgeriesListStoringAndValidation extends BaseTest {

    @Test
    public void surgeriesListValidation() throws Exception{
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        sp.saveSurgeriesListToExcelFromList();
        Assert.assertTrue(sp.surgeriesList().size()>1,"No surgeries found at pupular surgeries box in care page");

    }
}

