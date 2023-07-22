package com.lendwye.abtest.enums;

public enum PurchaseCostOptions {
    FIRST_COST_NUMBER(75F, 10L),
    SECOND_COST_NUMBER(10F, 20L),
    THIRD_COST_NUMBER(5F, 50L),
    FOURTH_COST_NUMBER(10F, 5L);

    private final Float frequency;
    private final Long value;

    private PurchaseCostOptions(Float frequency, Long value) {
        this.frequency = frequency;
        this.value = value;
    }

    public Float getFrequency() {
        return frequency;
    }

    public Long getValue() {
        return value;
    }
}
