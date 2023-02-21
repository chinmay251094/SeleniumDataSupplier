package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {
    private LoginPage() {
    }

    public static LoginPage useLoginPage() {
        return new LoginPage();
    }

    private final By TXT_USERNAME = By.xpath("//input[@name='username']");
    private final By TXT_PASSWORD = By.xpath("//input[@name='password' and @type='password']");
    private final By BTN_LOGIN = By.xpath("//button[@type='submit' and normalize-space()='Login']");

    public LoginPage setEmailAddress(String value) {
        sendKeys(TXT_USERNAME, Waits.VISIBLITY, value, "Email");
        return this;
    }

    public LoginPage setPassword(String value) {
        sendKeys(TXT_PASSWORD, Waits.NONE, value, "Password");
        return this;
    }

    public HomePage clickLogin() {
        click(BTN_LOGIN, Waits.NONE, "Login");
        return HomePage.useHomePage();
    }

    public boolean isLoginPageElementPresent() {
        return isElementPresent(BTN_LOGIN);
    }

    public HomePage performLogin(String username, String password) {
        setEmailAddress(username);
        setPassword(password);
        clickLogin();
        return HomePage.useHomePage();
    }
}
