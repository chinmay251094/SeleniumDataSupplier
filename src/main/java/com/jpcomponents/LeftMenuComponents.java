package com.jpcomponents;

public enum LeftMenuComponents {
    DASHBOARD("/dashboard"),
    SCHOOLS("/schools/school-list");

    private String menu = null;

    LeftMenuComponents(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }
}
