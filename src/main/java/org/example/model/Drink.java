package org.example.model;

import org.example.model.enums.DrinkSize;

import java.math.BigDecimal;

public record Drink(DrinkSize size, String flavor) {

    public String getDrinkDetails() {
        return size.name() + " " + flavor + " - $" + size.getPrice();
    }

    public BigDecimal getPrice() {
        return size.getPrice();
    }
}
