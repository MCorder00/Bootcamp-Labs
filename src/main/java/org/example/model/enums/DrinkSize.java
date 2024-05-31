package org.example.model.enums;

import java.math.BigDecimal;

public enum DrinkSize {
    SMALL(new BigDecimal("2.00")),
    MEDIUM(new BigDecimal("2.50")),
    LARGE(new BigDecimal("3.00"));

    private final BigDecimal price;

    DrinkSize(BigDecimal price) { this.price = price; }

    public BigDecimal getPrice() { return price; }
}
