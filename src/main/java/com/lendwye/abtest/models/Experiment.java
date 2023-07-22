package com.lendwye.abtest.models;

import java.util.Objects;

public class Experiment {
    private String parameter;
    private String value;

    public Experiment() {
    }

    public Experiment(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experiment that = (Experiment) o;
        return Objects.equals(parameter, that.parameter) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter, value);
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "parameter='" + parameter + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
