package com.pagecomponents;

public enum LeftMenuBarComponents {
    ADMIN("Admin"),
    PIM("PIM"),
    LEAVE("Leave"),
    TIME("Time"),
    MY_INFO("My Info");

    public String getMenuName() {
        return menuName;
    }

    private final String menuName;

    LeftMenuBarComponents(String menuName) {
        this.menuName = menuName;
    }
}
