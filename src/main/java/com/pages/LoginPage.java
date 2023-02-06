package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.factories.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {
    private static final By TXT_USERNAME = By.id("loginEmail");
    private static final By TXT_PASSWORD = By.id("loginPassword");
    private static final By BTN_LOGIN = By.xpath("//button[@type='submit' and normalize-space()='Login']");
    private static final By MESSAGE_INVALID_CREDENTIALS = By.xpath("//*[@id='toast-container']//div[normalize-space()='Invalid credentials.']");

    public LoginPage setUsername(String value) {
        sendKeys(TXT_USERNAME, Waits.VISIBLITY, value);
        return this;
    }

    public LoginPage setPassword(String value) {
        sendKeys(TXT_PASSWORD, Waits.NONE, value);
        return this;
    }

    public HomePage clickLogin() {
        click(BTN_LOGIN, Waits.NONE, "Login");
        synchronizeElements(2);
        return new HomePage();
    }

    public List<WebElement> getInvalidCredentialsMessage() {
        return WaitFactory.waitForElements(MESSAGE_INVALID_CREDENTIALS, Waits.PRESENCE);
    }
}
