package com.pages;

public enum PIMButtonComponents {
    ADD("Add"),
    SEARCH("Search"),
    RESET("Reset");

    public String getButton() {
        return button;
    }

    private String button;

    PIMButtonComponents(String button) {
        this.button = button;
    }
}
