package com.pages;

public enum ProfileComponents {
    ABOUT("About"),
    SUPPORT("Support"),
    CHANGE_PASSWORD("Change Password"),
    LOGOUT("Logout");

    public String getOptionsProfile() {
        return optionsProfile;
    }

    private final String optionsProfile;

    ProfileComponents(String optionsProfile) {
        this.optionsProfile = optionsProfile;
    }
}
