package com.jppages;

import com.base.BasePage;
import com.enums.Waits;
import com.jpcomponents.LeftMenuComponents;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private HomePage() {
    }

    public static HomePage useHomePage() {
        return new HomePage();
    }

    private static final String LEFT_MENU = "//app-left-sidebar/ul/li/a[@href='%s']";

    public void selectMenu(LeftMenuComponents leftMenuComponents) {
        WebElement menu = generateDynamicElement(LEFT_MENU, leftMenuComponents.getMenu());
        click(menu, Waits.CLICKABLE, String.valueOf(leftMenuComponents));
    }
}
