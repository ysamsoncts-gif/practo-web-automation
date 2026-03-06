package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;
import utilities.Log;

public class TC_25_ValidateDepartments extends BaseTest {

    @Test
    public void departmentListValidation() throws Exception{
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        Log.info("Starting test case: departmentListValidation");
        Assert.assertTrue(sp.ourDepartmentsList().size()>1,"No department found at our department section in care page");
        Log.info("Started to store the data into excel");
        sp.saveDepartmentsToExcelFromFlatList();
        Log.info("Successfully stored the data into excel");
        Log.info("Successfully validated departments list");
    }
}
