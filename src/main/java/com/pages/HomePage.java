package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import java.util.List;

import static com.factories.WaitFactory.waitForElements;

public class HomePage extends BasePage {
    private HomePage() {
    }

    public static HomePage getInstance() {
        return new HomePage();
    }

    private static final By BTN_LOGOUT = By.xpath("//a[@href='/logout' and normalize-space()='Logout']");
    private static final By MENU_ITEMS = By.xpath("//ul[@role='menu']/li/a");
    private static String dynamicSubMenuOptions = "//li[@class='nav-item has-treeview menu-is-opening menu-open']//a[@href='/Admin/%s/List']";
    private static final By BTN_ADD_NEW_CUSTOMER = By.xpath("//a[@href='/Admin/Customer/Create']");

    public boolean validateLogoutPresence() {
        return validateElementPresent(BTN_LOGOUT);
    }

    public boolean validateAddNewCustomerPresence() {
        return validateElementPresent(BTN_ADD_NEW_CUSTOMER);
    }

    public HomePage selectMenu(LeftMenuBarComponents menu) {
        selectDesiredOption(MENU_ITEMS, Waits.VISIBLITY, menu.getMenuName());
        return this;
    }

    public HomePage selectSubMenu(CustomerSubMenu subMenu) {
        click(generateDynamicByLocator(dynamicSubMenuOptions, subMenu.getSubMenuCustomers()), Waits.VISIBLITY, "Sub-Menu Customer");
        return this;
    }
}
