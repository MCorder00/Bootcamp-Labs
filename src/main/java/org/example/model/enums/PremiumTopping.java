package org.example.model.enums;

import java.math.BigDecimal;

public interface PremiumTopping {
    BigDecimal getPrice(SandwichSize size);
    BigDecimal getExtraPrice(SandwichSize size);
    String getName();
}