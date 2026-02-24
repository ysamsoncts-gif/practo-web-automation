package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SurgeriesPage;

public class TC_25_ValidateDepartments extends BaseTest {

    @Test
    public void departmentListValidation() throws Exception{
        SurgeriesPage sp = new SurgeriesPage(driver);
        sp.navigateToSurgeriesPage();
        Assert.assertTrue(sp.ourDepartmentsList().size()>1,"No department found at our department section in care page");
        sp.saveDepartmentsToExcelFromFlatList();

    }
}
