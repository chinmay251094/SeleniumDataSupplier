package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import com.google.common.util.concurrent.Uninterruptibles;
import com.pagecomponents.PIMButtonComponents;
import com.pagecomponents.PIMTextboxComponents;
import org.openqa.selenium.By;

import java.time.Duration;

public class PIMPage extends BasePage {
    private PIMPage() {
    }

    public static PIMPage usePIMPage() {
        return new PIMPage();
    }

    private static String buttons = "//button[normalize-space()='%s']";
    private static String textBox = "//input[@name='%s']";

    private static final By btnSave = By.xpath("//button[@type='submit']");

    public PIMPage clickButton(PIMButtonComponents buttonComponents) {
        click(generateDynamicByLocator(buttons, buttonComponents.getButton()), Waits.CLICKABLE, buttonComponents.getButton());
        return this;
    }

    public PIMPage setFirstName(PIMTextboxComponents textboxComponents, String value) {
        sendKeys(generateDynamicByLocator(textBox, textboxComponents.getTextbox()), Waits.NONE, value, "First Name");
        return this;
    }

    public PIMPage setLastName(PIMTextboxComponents textboxComponents, String value) {
        sendKeys(generateDynamicByLocator(textBox, textboxComponents.getTextbox()), Waits.NONE, value, "Last Name");
        return this;
    }

    public PIMPage addNewUser(PIMButtonComponents btnAdd, PIMTextboxComponents firstName, PIMTextboxComponents lastName, String fName, String lName) {
        clickButton(btnAdd);
        setFirstName(firstName, fName);
        setLastName(lastName, lName);
        click(btnSave, Waits.CLICKABLE, "Save");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        return this;
    }
}
