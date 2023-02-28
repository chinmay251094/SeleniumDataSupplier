package com.pagecomponents;

public enum PIMButtonComponents {
    ADD("Add"),
    SEARCH("Search"),
    RESET("Reset");

    public String getButton() {
        return button;
    }

    private String button;

    public String getTextBox() {
        return textBox;
    }

    private String textBox;

    PIMButtonComponents(String button) {
        this.button = button;
    }
}
