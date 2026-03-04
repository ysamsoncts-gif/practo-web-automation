package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.VideoConsultPage;
import utilities.Log;

public class TC_11_VideoConsultPageFormValidationNegativeCase extends BaseTest {
    @Test
    public void formValidation(){
        VideoConsultPage video = new VideoConsultPage(driver);
        Log.info("Test case 11 started:");
        video.navigateToVideoConsultPage();
        video.consultNowBtnClick();
        video.sendDetailsToForm("cold","476483684");
        video.specialityLocatorClick();
        video.continueBtnClick();
        Log.info("Validating Video Consult Page form negative case.");
        Assert.assertEquals(video.getOtpMsg(),"Not a valid mobile number.National number Eg: 081234 56789 International number Eg: +15107488230","Form error validation unsuccessful");
        Log.info("Test case 11 passed successfully.");
    }
}
