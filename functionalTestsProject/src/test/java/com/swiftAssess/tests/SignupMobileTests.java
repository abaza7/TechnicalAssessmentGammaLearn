package com.swiftAssess.tests;

import com.swiftAssess.driver.DriverManager;
import com.swiftAssess.pages.SignupPage;
import com.swiftAssess.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupMobileTests {

    @BeforeMethod
    public void setUp() {
        DriverManager.init(true);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.snap(DriverManager.get(), result.getName());
        }
        DriverManager.quit();
    }

    @Test(description = "Positive: required fields are filled, should succeed")
    public void signup_requiredOnly_valid_shouldSucceed() {
        String unique = String.valueOf(System.currentTimeMillis());
        boolean ok = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("Mohamed Ibrahim", "m" + unique + "@example.com", "United Arab Emirates", "12",
                        "College", "m" + unique)
                .submit()
                .isSuccess();
        Assert.assertTrue(ok, "Expected success on mobile emulation");
    }
}
