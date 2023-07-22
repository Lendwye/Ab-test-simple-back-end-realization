package com.lendwye.abtest.models;

import jakarta.persistence.*;

@Entity
public class DeviceTokenData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long device_token;
    private String button_color;
    private String button_color_name;
    private Long purchase_cost;
    private String purchase_cost_name;

    public Long getId() {
        return id;
    }

    public Long getDeviceToken() {
        return device_token;
    }

    public String getButtonColor() {
        return button_color;
    }

    public void setButtonColor(String button_color) {
        this.button_color = button_color;
    }

    public String getButtonColorName() {
        return button_color_name;
    }

    public void setButtonColorName(String button_color_name) {
        this.button_color_name = button_color_name;
    }

    public Long getPurchaseCost() {
        return purchase_cost;
    }

    public void setPurchaseCost(Long purchase_cost) {
        this.purchase_cost = purchase_cost;
    }

    public String getPurchaseCostName() {
        return purchase_cost_name;
    }

    public void setPurchaseCostName(String purchase_cost_name) {
        this.purchase_cost_name = purchase_cost_name;
    }

    public DeviceTokenData() {

    }

    public DeviceTokenData(Long device_token) {
        this.device_token =  device_token;
    }
}
