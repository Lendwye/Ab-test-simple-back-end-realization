package com.lendwye.abtest.enums;

public enum ButtonColorOptions {
    FIRST_COLOR_FREQUENCY(33.3F, "#FF0000"),
    SECOND_COLOR_FREQUENCY(33.3F, "#00FF00"),
    THIRD_COLOR_FREQUENCY(33.3F, "#0000FF");

    private Float frequency;
    private String value;

    private ButtonColorOptions(Float frequency, String value) {
        this.frequency = frequency;
        this.value = value;
    }

    public Float getFrequency() {
        return frequency;
    }

    public String getValue() {
        return value;
    }
}
