package org.example.model.enums;

import java.math.BigDecimal;

public enum Cheese implements PremiumTopping {
    AMERICAN,
    PROVOLONE,
    CHEDDAR,
    SWISS;

    @Override
    public BigDecimal getPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> new BigDecimal("0.75");
            case EIGHT_INCH -> new BigDecimal("1.50");
            case TWELVE_INCH -> new BigDecimal("2.25");
            default -> throw new IllegalArgumentException("Invalid sandwich size: " + size);
        };
    }

    @Override
    public BigDecimal getExtraPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> new BigDecimal("0.30");
            case EIGHT_INCH -> new BigDecimal("0.60");
            case TWELVE_INCH -> new BigDecimal("0.90");
            default -> throw new IllegalArgumentException("Invalid sandwich size: " + size);
        };
    }

    @Override
    public String getName() {
        return this.name();
    }
}