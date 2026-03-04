package testcases;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pageobjects.VideoConsultPage;

public class TC_12_VideoConsultPageFormValidation extends BaseTest {
    @Test
            public void formValidation() {
        VideoConsultPage video = new VideoConsultPage(driver);
        video.navigateToVideoConsultPage();
        video.consultNowBtnClick();
    }

}
