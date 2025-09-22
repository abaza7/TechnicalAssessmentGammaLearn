package com.swiftAssess.tests;

import com.swiftAssess.driver.DriverManager;
import com.swiftAssess.pages.SignupPage;
import com.swiftAssess.utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTests {

    @BeforeMethod
    public void setUp() {
        DriverManager.init(false);
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
                .fillRequiredFields("Mohamed Ibrahim", "m" + unique + "@example.com", "Egypt", "68",
                        "College", "m" + unique)
                .submit()
                .isSuccess();
        Assert.assertTrue(ok, "Expected success after submitting valid signup");
    }

    @Test(description = "Positive: all fields are filled, should succeed")
    public void signup_allFields_valid_shouldSucceed() {
        String unique = String.valueOf(System.currentTimeMillis());
        boolean ok = new SignupPage(DriverManager.get())
                .open()
                .fillAllFields("Global Inc.", "Omar Khalil", "m" + unique + "@example.com", "Germany", "61",
                        "College", "m" + unique)
                .submit()
                .isSuccess();
        Assert.assertTrue(ok, "Expected success after submitting valid signup");
    }

    @Test(description = "Negative: invalid email should show error")
    public void signup_invalidEmail_shouldFail() {
        String unique = String.valueOf(System.currentTimeMillis());
        SignupPage page = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("Ali Hassan", "not-an-email", "Egypt", "68", "College", "a" + unique)
                .submit();
        Assert.assertFalse(page.isSuccess(), "Should not succeed with invalid email format");
        Assert.assertEquals(page.getInvalidEmailError(), "Invalid email format", "Expected 'Invalid email format' validation error");
    }

    @Test(description = "Negative: empty email should show error")
    public void signup_emptyEmail_shouldFail() {
        String unique = String.valueOf(System.currentTimeMillis());
        SignupPage page = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("Ahmed Ghandour", "", "Egypt", "68", "College", "a" + unique)
                .submit();
        Assert.assertFalse(page.isSuccess(), "Should not succeed with empty email");
        Assert.assertEquals(page.getRequiredEmailError(), "Email is required", "Expected 'Email is required' validation error");
    }

    @Test(description = "Negative: empty name should show error")
    public void signup_emptyName_shouldFail() {
        String unique = String.valueOf(System.currentTimeMillis());
        SignupPage page = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("", "a" + unique + "@example.com", "Spain", "71", "General", "a" + unique)
                .submit();
        Assert.assertFalse(page.isSuccess(), "Should not succeed with empty name");
        Assert.assertEquals(page.getRequiredNameError(), "Contact name is required", "Expected 'Contact name is required' validation error");
    }

    @Test(description = "Negative: name with numbers only should show error")
    public void signup_numbersName_shouldFail() {
        String unique = String.valueOf(System.currentTimeMillis());
        SignupPage page = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("1337", "x" + unique + "@example.com", "Spain", "71", "General", "a" + unique)
                .submit();
        Assert.assertFalse(page.isSuccess(), "Should not succeed with invalid name");
        Assert.assertEquals(page.getInvalidNameError(), "Invalid name format", "Expected 'Invalid email format' validation error");
    }

    @Test(description = "Positive: account name has allowed special characters, required fields are filled, should succeed")
    public void signup_accountName_hyphen_valid_shouldSucceed() {
        String unique = String.valueOf(System.currentTimeMillis());
        System.out.println(unique);
        boolean ok = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("Mohamed Ibrahim", "m" + unique + "@example.com", "Egypt", "68",
                        "College", "moh-ibra")
                .submit()
                .isSuccess();
        Assert.assertTrue(ok, "Expected success after submitting valid signup");
    }

    @Test(description = "Negative: account name with a space should show error")
    public void signup_accountName_space_shouldFail() {
        String unique = String.valueOf(System.currentTimeMillis());
        SignupPage page = new SignupPage(DriverManager.get())
                .open()
                .fillRequiredFields("Israa Hossam", "x" + unique + "@example.com", "Spain", "71", "General", "israa hossam")
                .submit();
        Assert.assertFalse(page.isSuccess(), "Should not succeed with invalid name");
        Assert.assertEquals(page.getInvalidAccountNameError(), "Account name should be at least 5 characters. No spaces are allowed", "Expected 'Account name should be at least 5 characters. No spaces are allowed' validation error");
    }
}
