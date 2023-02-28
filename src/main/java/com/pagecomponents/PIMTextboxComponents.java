package com.pagecomponents;

public enum PIMTextboxComponents {
    FIRSTNAME("firstName"),
    LASTNAME("lastName");

    public String getTextbox() {
        return textBox;
    }

    private String textBox;

    PIMTextboxComponents(String textBox) {
        this.textBox = textBox;
    }
}
