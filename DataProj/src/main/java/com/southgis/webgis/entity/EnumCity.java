package com.southgis.webgis.entity;

public enum EnumCity {
    Position("position", 1),
    Salary("salary",2);

    private String name;
    private int code;

    EnumCity(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
