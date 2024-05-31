package org.example.model.enums;

import java.math.BigDecimal;

public enum ChipType {

    CLASSIC,
    SALT_VINEGAR,
    BBQ;

    public BigDecimal getPrice() {
        return new BigDecimal("1.50");
    }

}
