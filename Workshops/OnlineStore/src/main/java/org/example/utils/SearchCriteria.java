package org.example.utils;

public enum SearchCriteria {
    DEPARTMENT(1, "Search by department"),
    PRODUCT_NAME(2, "Search by product name"),
    PRICE_RANGE(3, "Search by price range");

    private final int value;
    private final String description;

    SearchCriteria(int value, String description) {
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