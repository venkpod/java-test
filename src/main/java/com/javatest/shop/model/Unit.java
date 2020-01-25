package com.javatest.shop.model;

import lombok.Getter;

public enum Unit {
    APPLE("single"),
    SOUP("Tin"),
    MILK("Bottle"),
    BREAD("Loaf");

    @Getter
    private String unit;

    private Unit(String unit){
        this.unit = unit;
    }

}
