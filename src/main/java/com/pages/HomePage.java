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
    private static final By MENU_ITEMS = By.xpath("//ul[@class='nav nav-pills nav-sidebar flex-column nav-legacy']");
    private static final By SELECTED_MENU = By.xpath("//li[@class='nav-item has-treeview menu-is-opening menu-open']/ul[@class='nav nav-treeview']");
    private static final By BTN_ADD_NEW_CUSTOMER = By.xpath("//a[@href='/Admin/Customer/Create']");

    public boolean validateLogoutPresence() {
        return validateElementPresent(BTN_LOGOUT);
    }

    public boolean validateAddNewCustomerPresence() {
        return validateElementPresent(BTN_ADD_NEW_CUSTOMER);
    }

    public HomePage selectMenu(LeftMenuBarComponents menu) {
        clickElementUnderListByText(MENU_ITEMS, Waits.VISIBLITY, "p", menu.getMenuName());
        return this;
    }

    public HomePage selectSubMenu(CustomerSubMenu subMenu) {
        clickElementUnderListByText(SELECTED_MENU, Waits.VISIBLITY, "p", subMenu.getSubMenuCustomers());
        return this;
    }
}
