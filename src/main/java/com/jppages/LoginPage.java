package com.jppages;

import com.base.BasePage;
import com.enums.Waits;
import com.jpcomponents.Districts;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private LoginPage() {
    }

    public static LoginPage useLoginPage() {
        return new LoginPage();
    }

    private static final By BTN_LOGIN_MODEL = By.id("openLoginModel");
    private static final By TXT_USERNAME = By.id("username");
    private static final By TXT_PASSWORD = By.id("password");
    private static final By LIST_OF_DISTRICTS = By.xpath("//ng-dropdown-panel[@role='listbox']//span");
    private static final By BTN_LOGIN = By.id("submitLogin");

    public LoginPage clickLoginModel() {
        click(BTN_LOGIN_MODEL, Waits.CLICKABLE, "Login With Clever");
        return this;
    }

    public LoginPage setUsername(String username) {
        sendKeys(TXT_USERNAME, Waits.CLICKABLE, username, "Username");
        return this;
    }

    public LoginPage setPassword(String password) {
        sendKeys(TXT_PASSWORD, Waits.NONE, password, "Password");
        return this;
    }

    public LoginPage selectDistrict(Districts districts) {
        getElement(By.tagName("ng-select"), Waits.NONE).click();
        selectDesiredOption(LIST_OF_DISTRICTS, Waits.VISIBLITY, districts.getDistricts());
        return this;
    }

    public LoginPage clickLogin() {
        click(BTN_LOGIN, Waits.CLICKABLE, "Login");
        return this;
    }
}
