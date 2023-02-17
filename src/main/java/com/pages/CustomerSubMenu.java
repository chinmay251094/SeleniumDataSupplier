package com.pages;

public enum CustomerSubMenu {
    CUSTOMER("Customers"),
    CUSTOMER_ROLE("Customer roles"),
    VENDOR("Vendors"),
    ACTIVITY_LOG("Activity log"),
    ACITIVITY_TYPES("Activity Types");

    public String getSubMenuCustomers() {
        return subMenuCustomers;
    }

    private final String subMenuCustomers;

    CustomerSubMenu(String subMenuCustomers) {
        this.subMenuCustomers = subMenuCustomers;
    }
}
