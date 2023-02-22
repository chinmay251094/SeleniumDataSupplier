package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.pagecomponents.LeftMenuBarComponents;
import com.pagecomponents.ProfileComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    private HomePage() {
    }

    public static HomePage useHomePage() {
        return new HomePage();
    }

    private static final By BTN_PROFILE = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private static final String MENU_OPTIONS = "//ul[@class='oxd-dropdown-menu']/li/a";
    private static final By MENU_ITEMS = By.xpath("//ul[@class='oxd-main-menu']/li");

    public boolean isProfileOptionPresent(ProfileComponents options) {
        WebElement element = getElement(BTN_PROFILE, Waits.CLICKABLE);
        List<WebElement> elements = element.findElements(By.tagName("a"));
        for (WebElement option : elements) {
            if (option.getText().equals(options.getOptionsProfile())) {
                return true;
            }
        }
        return false;
    }

    public HomePage selectProfileOption(ProfileComponents options) {
        selectValueFromCustomDropdown(BTN_PROFILE, MENU_OPTIONS, options.getOptionsProfile());
        return this;
    }

    public HomePage selectMenu(LeftMenuBarComponents menu) {
        selectDesiredOption(MENU_ITEMS, Waits.VISIBLITY, menu.getMenuName());
        return this;
    }
}
