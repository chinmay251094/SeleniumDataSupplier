package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import org.openqa.selenium.By;

public class MyInfoPage extends BasePage {
    private MyInfoPage() {}

    public static MyInfoPage useMyInfoPage() {
        return new MyInfoPage();
    }

    static String employeeId = null;
    private static final By TXT_FIRSTNAME = By.xpath("//input[@name='firstName']");
    private static final By TXT_LASTNAME = By.xpath("//input[@name='lastName']");
    private static final By TXT_EMPLOYEEID = By.xpath("//label[text()='Employee Id']/following::input[1]");
    private static final By BTN_INFO_SAVE = By.xpath("//*[normalize-space()='* Required']/following::button[1]");

    public MyInfoPage setFirstName(String firstName) {
        sendKeys(TXT_FIRSTNAME, Waits.VISIBLITY, firstName, "First Name");
        return this;
    }

    public MyInfoPage setLastName(String lastName) {
        sendKeys(TXT_LASTNAME, Waits.VISIBLITY, lastName, "Last Name");
        return this;
    }

    public void setEmployeeId() {
        employeeId = getAttributeValue(TXT_EMPLOYEEID, Waits.VISIBLITY);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public MyInfoPage clickSave() {
        click(BTN_INFO_SAVE, Waits.NONE, "Save");
        return this;
    }
}
