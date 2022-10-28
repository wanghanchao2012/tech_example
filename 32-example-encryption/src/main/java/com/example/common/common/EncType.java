package com.example.common.common;

public enum EncType {
    HEX("HEX"),
    BASE64("BASE64"),
    DEFAULT("DEFAULT");//系统默认编码

    private String type;

    EncType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
