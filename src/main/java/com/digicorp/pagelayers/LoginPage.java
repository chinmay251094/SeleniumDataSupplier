package com.digicorp.pagelayers;

import com.digicorp.enums.WaitTill;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.digicorp.utils.UsefulFunctionUtils.elementSynchronization;

public final class LoginPage extends BasePage {
    private final By txtEmail = By.id("loginEmail");
    private final By txtPassword = By.id("loginPassword");
    private final By btnLogin = By.xpath("//button[@type='submit' and normalize-space()='Login']");
    private final By messageInvalidCredentials = By.xpath("//div[@id='toast-container']//div[@aria-label='Invalid credentials.' and normalize-space()='Invalid credentials.']");

    public LoginPage setEmail(String value) throws Exception {
        sendKeys(txtEmail, value, WaitTill.PRESENCE, "Email edit-box");
        return this;
    }

    public LoginPage setPassword(String value) throws Exception {
        sendKeys(txtPassword, value, WaitTill.NONE, "Password edit-box");
        return this;
    }

    public DashboardPage clickSubmit() throws Exception {
        click(btnLogin, WaitTill.NONE, "Submit button");
        elementSynchronization();
        return new DashboardPage();
    }

    public List<WebElement> getErrorMessage() {
        return fetchElements(messageInvalidCredentials);
    }
}
