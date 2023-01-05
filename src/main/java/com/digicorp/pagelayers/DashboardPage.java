package com.digicorp.pagelayers;

import com.digicorp.enums.WaitTill;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public final class DashboardPage extends BasePage {
    private final By profile = By.xpath("//li[@class='oxd-userdropdown']");
    private final By optionsProfile = By.xpath("//ul[@class='oxd-dropdown-menu']/li/a");

    public DashboardPage clickProfile() throws Exception {
        click(profile, WaitTill.CLICKABLE, "Profile icon");
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        return this;
    }

    public LoginPage clickLogout() throws Exception {
        for (WebElement ele : fetchElements(optionsProfile)) {
            System.out.println(ele.getText());
        }
        selectOptions(fetchElements(optionsProfile), "Logout");
        return new LoginPage();
    }
}
