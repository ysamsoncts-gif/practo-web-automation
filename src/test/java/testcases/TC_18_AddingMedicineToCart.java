package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.MedicinePage;

public class TC_18_AddingMedicineToCart extends BaseTest {


    @Test
    public void addingMedicineToCart(){
        MedicinePage mp = new MedicinePage(driver);
        mp.navigateToLabTest();
        mp.navigateToMedicine();
        mp.navigateToSkin();
        mp.addToCart();
        Assert.assertTrue(
                driver.getCurrentUrl().toLowerCase().contains("cart"),
                "Medicines are not added to the cart"
        );

    }
}
