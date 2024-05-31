package org.example.model;

import org.example.model.enums.ChipType;

import java.math.BigDecimal;

public class Chip {
    private final ChipType chipType;

    public Chip(ChipType chipType) {
        this.chipType = chipType;
    }

    public String getChipDetails() {
        return chipType.name() + " Chips - $" + getPrice();
    }

    public BigDecimal getPrice() {
        return chipType.getPrice();
    }
}
