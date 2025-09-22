package com.swiftAssess.pages;

import com.swiftAssess.utils.Retry;
import com.swiftAssess.utils.ScreenshotUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SignupPage {
    private static final Logger log = LoggerFactory.getLogger(SignupPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By organizationField = By.id("SignUp1_txtOrganization");
    private final By nameField = By.id("SignUp1_txtName");
    private final By emailField = By.id("SignUp1_txtEmail");
    private final By countrySelect = By.id("SignUp1_ddlCountry");
    private final By accountTypeSelect = By.id("SignUp1_ddlAccountType");
    private final By accountNameField = By.id("SignUp1_txtAccountURL");
    private final By captchaImage = By.id("SignUp1_signUpCaptcha_CaptchaImageUP");
    private final By captchaField = By.id("SignUp1_signUpCaptcha_CaptchaTextBox");
    private final By submitBtn = By.id("SignUp1_btnSignUp");
    private final By successBannerHeadLocator = By.id("SignUp1_litSuccess_HeadPanel");
    private final By successBannerBodyLocator = By.id("SignUp1_litSuccess_DescPanel");
    private final By invalidEmailFormatErrorLocator = By.id("SignUp1_reqEmailFormat");
    private final By requiredEmailErrorLocator = By.id("SignUp1_reqEmail");
    private final By requiredNameErrorLocator = By.id("SignUp1_reqName");
    private final By invalidNameFormatErrorLocator = By.id("SignUp1_reqNameFormat");
    private final By invalidAccountNameFormatErrorLocator = By.id("SignUp1_reqComment");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public SignupPage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn));
        log.info("Sign Up Page Successfully Opened.");
        return this;
    }

    public SignupPage open() {
        return open("https://app.swiftassess.com/Signup");
    }

    public SignupPage fillRequiredFields(String name, String email, String countryText, String countryValue, String accountType, String accountName) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        selectFromDropdown(countrySelect, countryText, countryValue);
        selectFromDropdown(accountTypeSelect, accountType, accountType);

        driver.findElement(accountNameField).clear();
        driver.findElement(accountNameField).sendKeys(accountName);
        driver.findElement(captchaField);
        driver.findElement(captchaImage);
        log.info("Required Fields Successfully Filled.");
        return this;
    }

    public SignupPage fillAllFields(String organization, String name, String email, String countryText, String countryValue, String accountType, String accountName) {
        driver.findElement(organizationField).clear();
        driver.findElement(organizationField).sendKeys(organization);
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        selectFromDropdown(countrySelect, countryText, countryValue);
        selectFromDropdown(accountTypeSelect, accountType, accountType);

        driver.findElement(accountNameField).clear();
        driver.findElement(accountNameField).sendKeys(accountName);
        driver.findElement(captchaField);
        driver.findElement(captchaImage);
        log.info("All Fields Successfully Filled.");
        return this;
    }

    public void selectFromDropdown(By dropdownLocator, String visibleText, String fallbackValue) {
        try {
            Retry.attempt(3, 400, () -> {
                Select select = new Select(driver.findElement(dropdownLocator));
                try {
                    select.selectByVisibleText(visibleText);
                } catch (NoSuchElementException ex) {
                    log.error("Could not select from Dropdown by visible text, will try with value.");
                    if (fallbackValue != null && !fallbackValue.isBlank()) {
                        select.selectByValue(fallbackValue);
                        log.info("Selected from Dropdown with Value Successfully.");
                    } else {
                        log.error("Could not select from Dropdown with value.", ex);
                        throw ex;
                    }
                }
                return this;
            });
        } catch (RuntimeException e) {
            ScreenshotUtil.snap(driver, "selectFromDropdown_failure");
            log.error("Could not select from Dropdown.", e);
            throw e;
        }
    }

    public SignupPage submit() {
        WebElement signUpButton = driver.findElement(submitBtn);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signUpButton);
        log.info("Submit Button Successfully Clicked.");
        return this;
    }

    public boolean isSuccess() {
        try {
            WebElement bannerHead = wait.until(ExpectedConditions.visibilityOfElementLocated(successBannerHeadLocator));
            WebElement bannerBody = wait.until(ExpectedConditions.visibilityOfElementLocated(successBannerBodyLocator));
            if (bannerHead.isDisplayed() && bannerHead.getText().equals("An email confirmation has been sent to your address") && bannerBody.isDisplayed() && bannerBody.getText().equals("Click the link in the email to confirm your registration. If you do not see the email in your inbox, check your Junk folder.")) {
                log.info("Confirmation Message Successfully Found.");
                return true;
            }
        } catch (TimeoutException e) {
            return false;
        }
        return false;
    }

    public String getInvalidEmailError() {
        return getError(invalidEmailFormatErrorLocator);
    }

    public String getRequiredEmailError() {
        return getError(requiredEmailErrorLocator);
    }

    public String getRequiredNameError() {
        return getError(requiredNameErrorLocator);
    }

    public String getInvalidNameError() {
        return getError(invalidNameFormatErrorLocator);
    }

    public String getInvalidAccountNameError() {
        return getError(invalidAccountNameFormatErrorLocator);
    }

    private String getError(By errorLocator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator)).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }
}
