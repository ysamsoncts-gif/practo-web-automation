package testcases;


import basetest.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_01_GetURL extends BaseTest {



    @Test
    @Parameters({"baseUrl"})
    public void openHome(String baseUrl) {
        driver.get(baseUrl);
    }

}
