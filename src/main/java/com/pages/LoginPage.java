package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.factories.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.factories.WaitFactory.waitForElements;

public class LoginPage extends BasePage {
    private LoginPage() {
    }

    public static LoginPage getInstance() {
        return new LoginPage();
    }

    private final By TXT_EMAIL = By.id("Email");
    private final By TXT_PASSWORD = By.id("Password");
    private final By BTN_LOGIN = By.xpath("//button[normalize-space()='Log in']");

    public LoginPage setEmailAddress(String value) {
        sendKeys(TXT_EMAIL, Waits.VISIBLITY, value, "Email");
        return this;
    }

    public LoginPage setPassword(String value) {
        sendKeys(TXT_PASSWORD, Waits.NONE, value, "Password");
        return this;
    }

    public HomePage clickLogin() {
        click(BTN_LOGIN, Waits.NONE, "Login");
        return HomePage.getInstance();
    }
}
