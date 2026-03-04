package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CorporatePage;
import utilities.Log;

public class TC_26_ValidatingSupportMails extends BaseTest {
    @Test
    public void validatingSupportMail()
    {
        Log.info("Starting test case: ValidatingSupportMails");
        CorporatePage cp = new CorporatePage(driver);
        cp.navigateToContactPage();
        Assert.assertEquals(cp.getSupportMailLnk(),"support@practo.com","Support email is incorrect");
        Assert.assertEquals(cp.getNodalOfficerMailLnk(),"practo-nodal-officer-team@practo.com","Nodal Officer email is incorrect");
        Assert.assertEquals(cp.getgrievanceOfficerMail(),"practo-grievance-officer-team@practo.com","Grievance Officer email is incorrect");
        Log.info("Ending test case: ValidatingSupportMails");
    }
}
