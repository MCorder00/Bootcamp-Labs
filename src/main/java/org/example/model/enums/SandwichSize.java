package org.example.model.enums;

import java.math.BigDecimal;

    public enum SandwichSize {
        FOUR_INCH(new BigDecimal("5.50")),
        EIGHT_INCH(new BigDecimal("7.00")),
        TWELVE_INCH(new BigDecimal("8.50"));
    
        private final BigDecimal price;
    
        SandwichSize(BigDecimal price) {
            this.price = price;
        }
    
        public BigDecimal getPrice() {
            return price;
        }
    }
