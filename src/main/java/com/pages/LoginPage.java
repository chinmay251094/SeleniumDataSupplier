package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.factories.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.factories.WaitFactory.waitForElements;

public class LoginPage extends BasePage {
    private static final By TXT_USERNAME = By.id("loginEmail");
    private static final By TXT_PASSWORD = By.id("loginPassword");
    private static final By BTN_LOGIN = By.xpath("//button[@type='submit' and normalize-space()='Login']");
    private static final By MESSAGE_INVALID_CREDENTIALS = By.xpath("//*[@id='toast-container']//div[normalize-space()='Invalid credentials.']");

    public LoginPage setEmailAddress(String value) {
        smartWait();
        sendKeys(TXT_USERNAME, Waits.VISIBLITY, value, "Email Address");
        return this;
    }

    public LoginPage setPassword(String value) {
        synchronizeElements(1);
        sendKeys(TXT_PASSWORD, Waits.NONE, value, "Password");
        return this;
    }

    public HomePage clickLogin() {
        click(BTN_LOGIN, Waits.NONE, "Login");
        synchronizeElements(1);
        takeScreenshot("Dashboard");
        return new HomePage();
    }

    public List<WebElement> getInvalidCredentialsMessage() {
        return waitForElements(MESSAGE_INVALID_CREDENTIALS, Waits.PRESENCE);
    }
}
