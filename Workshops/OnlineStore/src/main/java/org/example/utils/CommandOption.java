package org.example.utils;

public enum CommandOption {
    DISPLAY_CART(1, "Display Cart"),
    DISPLAY_PRODUCTS(2, "Display Products"),
    EXIT(3, "Exit");

    private final int value;
    private final String description;

    CommandOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}