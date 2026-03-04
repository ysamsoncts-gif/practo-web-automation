package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.VideoConsultPage;
import utilities.Log;

public class TC_12_VideoConsultPageFormValidation extends BaseTest {
    @Test
        public void formValidation() throws InterruptedException {
        VideoConsultPage video = new VideoConsultPage(driver);
        Log.info("Test case 12 started:");
        video.navigateToVideoConsultPage();
        video.consultNowBtnClick();
        video.sendDetailsToForm("fever","7865465451");
        video.specialityLocatorClick();
        video.continueBtnClick();
        Log.info("Validating Video Consult Page form.");
       Assert.assertEquals(video.getOtpMsg(),"We have sent you an OTP on","Form validation unsuccessful");
       Log.info("Test case 12 passed successfully.");
    }

}
