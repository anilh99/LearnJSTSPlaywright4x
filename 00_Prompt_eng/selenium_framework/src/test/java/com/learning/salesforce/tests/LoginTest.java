package com.learning.salesforce.tests;

import java.time.Duration;
import java.util.UUID;

import com.learning.salesforce.pages.LoginPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(20);
    private WebDriver driver;
    private String baseUrl;
    private String validUsername;
    private String validPassword;
    private boolean headless;

    @BeforeTest(alwaysRun = true)
    public void loadConfiguration() {
        baseUrl = setting("salesforce.baseUrl", "SALESFORCE_BASE_URL", "https://login.salesforce.com/?locale=in");
        validUsername = setting("salesforce.username", "SALESFORCE_USERNAME", "");
        validPassword = setting("salesforce.password", "SALESFORCE_PASSWORD", "");
        headless = Boolean.parseBoolean(setting("salesforce.headless", "SALESFORCE_HEADLESS", "true"));
    }

    @BeforeMethod(alwaysRun = true)
    public void createBrowser() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1440,1000");
            if (headless) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
            driver.manage().window().setSize(new Dimension(1440, 1000));
        } catch (RuntimeException exception) {
            closeBrowserAfterSetupFailure();
            throw new IllegalStateException("Unable to start the Chrome WebDriver session", exception);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException exception) {
                throw new IllegalStateException("Unable to close the Chrome WebDriver session", exception);
            } finally {
                driver = null;
            }
        }
    }

    @Test
    public void validCredentialsAdvanceBeyondCredentialForm() {
        if (validUsername.isBlank() || validPassword.isBlank()) {
            throw new SkipException("Set SALESFORCE_USERNAME and SALESFORCE_PASSWORD to run the valid-login test");
        }

        try {
            driver.get(baseUrl);
            LoginPage loginPage = new LoginPage(driver, WAIT_TIMEOUT);
            loginPage.submitCredentials(validUsername, validPassword);
            Assert.assertTrue(loginPage.waitForCredentialFormToClose(),
                    "Valid credentials should advance beyond the username and password form");
        } catch (RuntimeException exception) {
            Assert.fail("Valid-credential login flow failed", exception);
        }
    }

    @Test
    public void invalidCredentialsDisplayLoginError() {
        try {
            driver.get(baseUrl);
            LoginPage loginPage = new LoginPage(driver, WAIT_TIMEOUT);
            String invalidUsername = "selenium.invalid." + UUID.randomUUID() + "@example.invalid";
            loginPage.submitCredentials(invalidUsername, UUID.randomUUID().toString());
            Assert.assertFalse(loginPage.waitForLoginError().isBlank(),
                    "Invalid credentials should display a login error");
        } catch (RuntimeException exception) {
            Assert.fail("Invalid-credential validation failed", exception);
        }
    }

    @Test
    public void rememberMeCanBeSelected() {
        try {
            driver.get(baseUrl);
            LoginPage loginPage = new LoginPage(driver, WAIT_TIMEOUT);
            loginPage.setRememberMe(true);
            Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember Me should be selected");
        } catch (RuntimeException exception) {
            Assert.fail("Remember Me control verification failed", exception);
        }
    }

    private String setting(String property, String environmentVariable, String defaultValue) {
        String value = System.getProperty(property);
        if (value == null || value.isBlank()) {
            value = System.getenv(environmentVariable);
        }
        return value == null || value.isBlank() ? defaultValue : value;
    }

    private void closeBrowserAfterSetupFailure() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException ignored) {
                driver = null;
            }
        }
    }
}