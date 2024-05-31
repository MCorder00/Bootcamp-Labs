package org.example.model.enums;

import java.math.BigDecimal;

public enum BreadType {
    WHITE,
    WHEAT,
    RYE,
    WRAP;

    public BigDecimal getPrice() {
        return new BigDecimal("0.50");
    }
}