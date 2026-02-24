package testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.FindDoctorsPage;

public class TC_05_FindDoctorsPagePhoneErrorMessageValidation extends BaseTest {

    @Test
    public void verifyErrorMessage() throws InterruptedException {
        FindDoctorsPage doctors = new FindDoctorsPage(driver);

        doctors.navigateToFindDoctorsTab();

        doctors.findDoctorsTabSearchLocation();
        doctors.findDoctorsTabSearchDoctors();

        doctors.selectGenderFilter();
        doctors.selectFeedbackFilter();
        doctors.selectExperienceFilter();
        doctors.selectALlFilters();

        doctors.navigateToDoctorBookingTab();

        doctors.selectBookingTime();

        doctors.sendPhoneNumber();

        System.out.println("Test Case 04 :");
        System.out.println("Invalid phone number error is displayed (Test case passed) :"+doctors.validateErrorMessage());
        Assert.assertEquals(doctors.validateErrorMessage(),"Number is not valid","Phone number error message not displayed");
    }
}
