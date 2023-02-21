package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import org.openqa.selenium.By;

public class PIMPage extends BasePage {
    private PIMPage() {
    }

    public static PIMPage usePIMPage() {
        return new PIMPage();
    }

    private static String buttons = "//button[normalize-space()='%s']";

    public PIMPage clickButton(PIMButtonComponents buttonComponents) {
        String xpath = String.format(buttons, buttonComponents.getButton());
        click(getElement(By.xpath(xpath), Waits.CLICKABLE), Waits.NONE, buttonComponents.getButton());
        return this;
    }
}
