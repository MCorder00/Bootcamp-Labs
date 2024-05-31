package org.example.model.enums;

import java.math.BigDecimal;

public enum Meat implements PremiumTopping {
    SALAMI,
    ROAST_BEEF,
    CHICKEN,
    BACON,
    STEAK,
    HAM;

    @Override
    public BigDecimal getPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> new BigDecimal("1.00");
            case EIGHT_INCH -> new BigDecimal("2.00");
            case TWELVE_INCH -> new BigDecimal("3.00");
            default -> throw new IllegalArgumentException("Invalid sandwich size: " + size);
        };
    }

    @Override
    public BigDecimal getExtraPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> new BigDecimal("0.50");
            case EIGHT_INCH -> new BigDecimal("1.00");
            case TWELVE_INCH -> new BigDecimal("1.50");
            default -> throw new IllegalArgumentException("Invalid sandwich size: " + size);
        };
    }

    @Override
    public String getName() {
        return this.name();
    }

}