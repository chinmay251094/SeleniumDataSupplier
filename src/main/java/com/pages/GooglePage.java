package com.pages;

import com.base.BasePage;
import com.enums.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GooglePage extends BasePage {
    By txtSearchBox = By.name("q");

    public GooglePage setSearchData(String value) {
        sendKeys(txtSearchBox, Waits.PRESENCE, value);
        pressKeys(txtSearchBox, Waits.NONE, Keys.ENTER);
        return this;
    }
}
