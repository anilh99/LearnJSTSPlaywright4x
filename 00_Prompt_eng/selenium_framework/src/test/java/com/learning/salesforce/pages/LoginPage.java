package com.learning.salesforce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMe;

    @FindBy(xpath = "//*[@id='error']")
    private WebElement loginError;

    public LoginPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, timeout);
    }

    public void submitCredentials(String user, String pass) {
        try {
            wait.until(ExpectedConditions.visibilityOf(username)).clear();
            username.sendKeys(user);
            List<WebElement> passwordFields = driver.findElements(By.xpath("//input[@id='password']"));
            if (passwordFields.stream().noneMatch(WebElement::isDisplayed)) {
                wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            }
            wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to submit the Salesforce login form", exception);
        }
    }

    public String waitForLoginError() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginError)).getText().trim();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Salesforce login error was not displayed", exception);
        }
    }

    public boolean waitForCredentialFormToClose() {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(loginButton));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Salesforce did not advance beyond the credential form", exception);
        }
    }

    public void setRememberMe(boolean selected) {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(rememberMe));
            if (checkbox.isSelected() != selected) {
                checkbox.click();
            }
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to update the Remember Me setting", exception);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(rememberMe)).isSelected();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to read the Remember Me setting", exception);
        }
    }
}