package com.lenovo.smarttraffic.entity;

public class Car02_item {
    private String index;
    private String value;
    private String status;
    private int image;
    private int color;

    public Car02_item() {
    }

    public Car02_item(String index, String value, String status, int image, int color) {
        this.index = index;
        this.value = value;
        this.status = status;
        this.image = image;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Car02_item(String index, String value, String status, int image) {
        this.index = index;
        this.value = value;
        this.status = status;
        this.image = image;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
